package com.example.cardiosurgeryillustrator.ui.screens.patient.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.ForumTopBar
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.LastMessageForum
import com.example.cardiosurgeryillustrator.ui.components.patient.message_bottom.MessageBottomBar
import com.example.cardiosurgeryillustrator.view_models.patient.community.CommunityViewModel

@Composable
fun ForumScreen(
    modifier: Modifier = Modifier,
    viewModel: CommunityViewModel = viewModel(),
    navController: NavController,
    topicId: String
) {
    val allTopics by viewModel.topics.collectAsState(emptyList())
    val topic = allTopics.find { it.id == topicId }
    val messages by viewModel.getMessagesForTopic(topicId).collectAsState(emptyList())
    var messageText by remember { mutableStateOf(TextFieldValue()) }

    Scaffold(
        bottomBar = {
            MessageBottomBar(
                messageText = messageText,
                onMessageTextChange = { messageText = it },
                onSendClick = {
                    if (messageText.text.isNotEmpty()) {
                        println("Mensagem enviada no fórum: ${messageText.text}")
                        viewModel.sendMessageToTopic(topicId, messageText.text)
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
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                topic?.let {
                    ForumTopBar(
                        topic = it,
                        backgroundImageRes = R.drawable.img_defaul,
                        modifier = Modifier.fillMaxWidth(),
                        isTopicSaved = topicId in viewModel.currentUser.value.savedTopics,
                        onSaveToggle = { newState ->
                            viewModel.toggleSavedTopic(topicId, newState)
                        },
                        onBackClick = { navController.popBackStack() }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(messages.sortedBy { it.timestamp }) { message ->
                            LastMessageForum(
                                userAvatar = R.drawable.avatar_1,
                                message = message.content
                            )
                        }
                    }
                }
            }
        }
    )
}