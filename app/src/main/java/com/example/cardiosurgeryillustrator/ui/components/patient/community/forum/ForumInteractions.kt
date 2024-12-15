package com.example.cardiosurgeryillustrator.ui.components.patient.community.forum

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@Composable
fun ForumInteractions(
    likes: Int,
    comments: Int,
    isLiked: Boolean,
    isSaved: Boolean,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onLikeClick) {
                    Icon(
                        painter = painterResource(id = if (isLiked) R.drawable.ic_liked else R.drawable.ic_unliked),
                        contentDescription = "Curtir"
                    )
                }
                Text(text = likes.toString(), style = Typography.bodySmall)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onCommentClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_comment),
                        contentDescription = "Comentar"
                    )
                }
                Text(text = comments.toString(), style = Typography.bodySmall)
            }
        }

        IconButton(onClick = onSaveClick) {
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
        likes = 2000,
        comments = 500,
        isLiked = false,
        isSaved = false,
        onLikeClick = {},
        onCommentClick = {},
        onSaveClick = {}
    )
}
