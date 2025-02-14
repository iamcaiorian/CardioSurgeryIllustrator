package com.example.cardiosurgeryillustrator.ui.screens.patient.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.patient.community.Message
import com.example.cardiosurgeryillustrator.models.patient.community.topic.Topic
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.ForumTopBar
import com.example.cardiosurgeryillustrator.ui.components.patient.message_bottom.MessageBottomBar
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Zinc300
import com.example.cardiosurgeryillustrator.view_models.patient.community.ForumViewModel
import com.example.cardiosurgeryillustrator.view_models.patient.community.ForumViewModelFactory

@Composable
fun ForumScreen(
    modifier: Modifier = Modifier,
    viewModel: ForumViewModel = viewModel(factory = ForumViewModelFactory(ForumRepository())),
    navController: NavController,
    topicId: String
) {
    val allTopics by viewModel.topics.collectAsState(emptyList())
    val topic = allTopics.find { it.id == topicId }
    val messages by viewModel.messages.collectAsState(emptyList())

    var messageText by remember { mutableStateOf(TextFieldValue("")) }
    val listState = rememberLazyListState()

    LaunchedEffect(topicId) {
        viewModel.getAllMessagesForForum(topicId)
    }

    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size)
    }

    Scaffold(
        bottomBar = {
            MessageBottomBar(
                messageText = messageText,
                onMessageTextChange = { messageText = it },
                onSendClick = {
                    if (messageText.text.isNotEmpty()) {
                        viewModel.sendMessageToTopic(topicId, userId = "1", messageText.text)
                        messageText = TextFieldValue("")
                    }
                },
                placeholder = "Digite sua mensagem..."
            )
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .background(Color.White)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
                topic?.let {
                    ForumTopBar(
                        topic = it,
                        backgroundImageRes = R.drawable.img_defaul,
                        modifier = Modifier.fillMaxWidth(),
                        isTopicSaved = false,
                        onSaveToggle = {},
                        onBackClick = { navController.popBackStack() }
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        state = listState
                    ) {
                        items(messages) { message ->
                            MessageBubble(
                                content = message.content,
                                isUserMessage = message.userId == "1",
                                showAvatar = true,
                                userAvatar = if (message.userId == "1") R.drawable.avatar_1 else R.drawable.avatar_1
                            )
                        }
                    }
                }
            }
        }
    )
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


@Preview(showBackground = true)
@Composable
fun PreviewForumScreen() {
    val navController = rememberNavController()
    var messageText by remember { mutableStateOf(TextFieldValue("")) }
    val listState = rememberLazyListState()

    val messages = listOf(
        Message(userId = "1", content = "Olá, alguém pode me explicar sobre a cirurgia?"),
        Message(userId = "2", content = "Claro! A cirurgia de ponte de safena é um procedimento comum para tratar obstruções nas artérias coronárias."),
        Message(userId = "2", content = "Ela melhora o fluxo sanguíneo e reduz os sintomas de angina."),
        Message(userId = "3", content = "Já passei por esse procedimento, foi tranquilo."),
        Message(userId = "1", content = "Que bom saber! Quanto tempo foi a recuperação?"),
        Message(userId = "1", content = "Preciso me preparar para isso.")
    )

    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size)
    }

    val defaultTopic = Topic(
        id = "1",
        userId = "123",
        theme = "Cardiologia",
        title = "Cirurgia de Ponte de Safena",
        messages = messages,
        likes = 10,
        comments = 3,
        timestamp = System.currentTimeMillis()
    )

    Scaffold(
        bottomBar = {
            MessageBottomBar(
                messageText = messageText,
                onMessageTextChange = { messageText = it },
                onSendClick = {
                    if (messageText.text.isNotEmpty()) {
                        messageText = TextFieldValue("")
                    }
                },
                placeholder = "Digite sua mensagem..."
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
                ForumTopBar(
                    topic = defaultTopic,
                    backgroundImageRes = R.drawable.img_defaul,
                    modifier = Modifier.fillMaxWidth(),
                    isTopicSaved = false,
                    onSaveToggle = {},
                    onBackClick = { }
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    state = listState
                ) {
                    itemsIndexed(messages) { index, message ->
                        val previousUserId = if (index > 0) messages[index - 1].userId else null
                        val showAvatar = previousUserId != message.userId

                        MessageBubble(
                            content = message.content,
                            isUserMessage = message.userId == "1",
                            showAvatar = showAvatar,
                            userAvatar = if (message.userId == "1") R.drawable.avatar_1 else R.drawable.avatar_1
                        )
                    }
                }
            }
        }
    )
}
