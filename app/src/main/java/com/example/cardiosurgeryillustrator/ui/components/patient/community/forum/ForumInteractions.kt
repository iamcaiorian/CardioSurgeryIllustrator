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
import com.example.cardiosurgeryillustrator.models.patient.community.forum.Forum
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.view_models.patient.community.CommunityViewModel

@Composable
fun ForumInteractions(
    forum: Forum,
    viewModel: CommunityViewModel,
    modifier: Modifier = Modifier
) {
    var likes by remember { mutableStateOf(forum.likes) }
    var isLiked by remember { mutableStateOf(forum.isLiked) }
    var isSaved by remember { mutableStateOf(forum.isFavorite) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    viewModel.likeForum(forum.id)
                    isLiked = true
                    likes++
                }) {
                    Icon(
                        painter = painterResource(id = if (isLiked) R.drawable.ic_liked else R.drawable.ic_unliked),
                        contentDescription = "Curtir"
                    )
                }
                Text(text = likes.toString(), style = Typography.bodySmall)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_comment),
                    contentDescription = "Comentar"
                )
                Text(text = forum.comments.toString(), style = Typography.bodySmall)
            }
        }

        IconButton(onClick = {
            viewModel.saveForum(forum.id)
            isSaved = !isSaved
        }) {
            Icon(
                painter = painterResource(id = if (isSaved) R.drawable.ic_saved else R.drawable.ic_unsaved),
                contentDescription = "Salvar"
            )
        }
    }
}