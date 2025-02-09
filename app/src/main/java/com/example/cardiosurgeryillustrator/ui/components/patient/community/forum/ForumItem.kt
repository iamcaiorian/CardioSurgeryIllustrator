package com.example.cardiosurgeryillustrator.ui.components.patient.community.forum

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.patient.community.Topic
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc100
import com.example.cardiosurgeryillustrator.ui.theme.Zinc300
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
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_defaul),
                    contentDescription = "Imagem de Fundo do Fórum",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.7f),
                                    Color.Transparent
                                ),
                                startY = Float.POSITIVE_INFINITY,
                                endY = 0f
                            )
                        )
                )

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = topic.theme,
                        style = Typography.headlineLarge,
                        color = Zinc100
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = topic.title,
                        style = Typography.bodyLarge,
                        color = Zinc300
                    )
                }
            }

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
