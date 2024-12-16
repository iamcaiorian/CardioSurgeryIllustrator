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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@Composable
fun ForumInteractions(
    initialLikes: Int,
    initialComments: Int,
    initialIsLiked: Boolean,
    initialIsSaved: Boolean,
    modifier: Modifier = Modifier
) {
    var likes by remember { mutableStateOf(initialLikes) }
    var comments by remember { mutableStateOf(initialComments) }
    var isLiked by remember { mutableStateOf(initialIsLiked) }
    var isSaved by remember { mutableStateOf(initialIsSaved) }

    fun toggleLike() {
        isLiked = !isLiked
        likes = if (isLiked) likes + 1 else likes - 1
    }

    fun addComment() {
        // Aqui você pode implementar uma lógica para abrir uma caixa de diálogo ou navegação para comentar
    }

    fun toggleSave() {
        isSaved = !isSaved
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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
                IconButton(onClick = { addComment() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_comment),
                        contentDescription = "Comentar"
                    )
                }
                Text(text = comments.toString(), style = Typography.bodySmall)
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

@Preview
@Composable
private fun ForumInteractionsPreview() {
    ForumInteractions(
        initialLikes = 2000,
        initialComments = 500,
        initialIsLiked = false,
        initialIsSaved = false
    )
}
