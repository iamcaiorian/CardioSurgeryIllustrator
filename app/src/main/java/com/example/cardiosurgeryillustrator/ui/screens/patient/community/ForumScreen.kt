package com.example.cardiosurgeryillustrator.ui.screens.patient.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.patient.community.forum.Forum
import com.example.cardiosurgeryillustrator.repository.patient.community.CommentRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.ForumTopBar
import com.example.cardiosurgeryillustrator.ui.components.patient.message_bottom.MessageBottomBar
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Zinc300
import com.example.cardiosurgeryillustrator.view_models.patient.community.CommunityViewModel
import com.example.cardiosurgeryillustrator.view_models.patient.community.ForumViewModel
import com.example.cardiosurgeryillustrator.view_models.patient.community.ForumViewModelFactory

@Composable
fun ForumScreen(
    navController: NavController,
    forumId: String,
    viewModel: ForumViewModel = viewModel(factory = ForumViewModelFactory(ForumRepository(), CommentRepository(), PatientRepository())),
    communityViewModel: CommunityViewModel
) {
    val forumResponse by viewModel.forum.collectAsState()
    val messages by viewModel.messages.collectAsState()
    var messageText by remember { mutableStateOf(TextFieldValue("")) }
    val listState = rememberLazyListState()

    val forum = forumResponse?.let {
        Forum(
            id = it.id,
            userId = it.creatorId,
            theme = it.theme,
            title = it.title,
            commentResponse = it.comments,
            likes = it.likesAmount,
            comments = it.commentsAmount,
            timestamp = it.createdAt
        )
    }

    LaunchedEffect(forumId) { viewModel.loadForum(forumId, "1") }
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.lastIndex)
        }
    }

    Scaffold(
        bottomBar = {
            MessageBottomBar(
                messageText = messageText,
                onMessageTextChange = { messageText = it },
                onSendClick = {
                    if (messageText.text.isNotEmpty()) {
                        viewModel.sendMessageToForum(forumId, messageText.text)
                        messageText = TextFieldValue("")
                    }
                },
                placeholder = "Digite sua mensagem..."
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            forum?.let {
                ForumTopBar(
                    forum = it,
                    viewModel = communityViewModel,
                    backgroundImageRes = R.drawable.img_defaul,
                    modifier = Modifier.fillMaxWidth(),
                    onBackClick = { navController.popBackStack() }
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    state = listState
                ) {
                    items(messages) { message ->
                        MessageBubble(
                            content = message.content,
                            isUserMessage = message.id == "1",
                            showAvatar = true,
                            userAvatar = R.drawable.avatar_1
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MessageBubble(
    content: String,
    isUserMessage: Boolean,
    showAvatar: Boolean,
    userAvatar: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = if (showAvatar) 8.dp else 2.dp),
        horizontalArrangement = if (isUserMessage) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        if (!isUserMessage && showAvatar) {
            Image(
                painter = painterResource(id = userAvatar),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
        } else if (!isUserMessage) {
            Spacer(modifier = Modifier.width(44.dp))
        }
        Box(
            modifier = Modifier
                .background(
                    color = if (isUserMessage) Blue700 else Zinc300,
                    shape = if (isUserMessage) RoundedCornerShape(16.dp, 16.dp, 2.dp, 16.dp) else RoundedCornerShape(16.dp, 16.dp, 16.dp, 2.dp)
                )
                .padding(12.dp)
                .widthIn(max = 250.dp)
        ) {
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = if (isUserMessage) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}
