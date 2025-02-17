package com.example.cardiosurgeryillustrator.ui.components.patient.community.forum

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.patient.community.forum.Topic
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@Composable
fun ForumInteractions(
    topic: Topic,
    isTopicSaved: Boolean,
    onSaveToggle: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var likes by remember { mutableStateOf(topic.likes) }
    var isLiked by remember { mutableStateOf(false) }
    var isSaved by remember { mutableStateOf(isTopicSaved) }

    fun toggleLike() {
        isLiked = !isLiked
        likes += if (isLiked) 1 else -1
    }

    fun toggleSave() {
        isSaved = !isSaved
        onSaveToggle(topic.id, isSaved)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { toggleLike() }) {
                    Icon(
                        painter = painterResource(id = if (isLiked) R.drawable.ic_liked else R.drawable.ic_unliked),
                        contentDescription = "Curtir"
                    )
                }
                Text(text = likes.toString(), style = Typography.bodySmall)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { /* Adicionar lógica de comentário */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_comment),
                        contentDescription = "Comentar"
                    )
                }
                Text(text = topic.comments.toString(), style = Typography.bodySmall) // Mantendo os comentários fixos
            }
        }

        IconButton(onClick = { toggleSave() }) {
            Icon(
                painter = painterResource(id = if (isSaved) R.drawable.ic_saved else R.drawable.ic_unsaved),
                contentDescription = "Salvar"
            )
        }
    }
}

