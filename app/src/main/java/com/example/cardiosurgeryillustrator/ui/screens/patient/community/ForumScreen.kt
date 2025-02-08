package com.example.cardiosurgeryillustrator.ui.screens.patient.community

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.ForumBottomBar
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.ForumHeader
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.ForumInteractions
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.LastMessageForum
import com.example.cardiosurgeryillustrator.view_models.patient.community.CommunityViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForumScreen(
    modifier: Modifier = Modifier,
    viewModel: CommunityViewModel = viewModel(),
    topicId: String
) {
    val allTopics by viewModel.topics.collectAsState(emptyList())
    val topic = allTopics.find { it.id == topicId }
    val messages by viewModel.getMessagesForTopic(topicId).collectAsState(emptyList())

    val currentUser by viewModel.currentUser.collectAsState()
    val isSaved = topicId in currentUser.savedTopics

    Scaffold(
        bottomBar = { ForumBottomBar(modifier = Modifier) },
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
                    ForumHeader(
                        title = it.title,
                        subtitle = it.theme,
                        backgroundImageRes = R.drawable.img_defaul,
                        modifier = Modifier.fillMaxWidth()
                    )

                    ForumInteractions(
                        topic = topic,
                        isTopicSaved = isSaved,
                        onSaveToggle = { topicId, newState ->
                            viewModel.toggleSavedTopic(topicId, newState)
                        }
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
