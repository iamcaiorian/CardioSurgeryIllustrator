package com.example.cardiosurgeryillustrator.ui.components.patient.community.forum

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.patient.community.Topic
import com.example.cardiosurgeryillustrator.view_models.patient.community.CommunityViewModel

@Composable
fun ForumItem(
    topic: Topic,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CommunityViewModel = viewModel()
) {
    val currentUser by viewModel.currentUser.collectAsState()
    val isSaved = topic.id in currentUser.savedTopics

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent, shape = RoundedCornerShape(12.dp))
            .clickable {
                try {
                    navController.navigate("forum_screen/${topic.id}")
                } catch (e: Exception) {
                    Log.e("ForumItem", "Erro ao navegar para o fórum", e)
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            ForumHeader(
                title = topic.title,
                subtitle = topic.theme,
                backgroundImageRes = R.drawable.img_defaul
            )
            ForumInteractions(
                topic = topic,
                isTopicSaved = isSaved,
                onSaveToggle = { topicId, newState ->
                    viewModel.toggleSavedTopic(topicId, newState)
                }
            )
            LastMessageForum(
                userAvatar = R.drawable.avatar_1,
                message = "Última mensagem..."
            )
        }
    }
}
