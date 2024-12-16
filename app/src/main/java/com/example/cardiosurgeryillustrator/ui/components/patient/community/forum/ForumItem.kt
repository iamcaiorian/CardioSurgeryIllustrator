package com.example.cardiosurgeryillustrator.ui.components.patient.community.forum

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R


@Composable
fun ForumItem(
    title: String,
    subtitle: String,
    backgroundImageRes: Int,
    userAvatar: Int,
    message: String,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {  }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            ForumHeader(
                title = title,
                subtitle = subtitle,
                backgroundImageRes = backgroundImageRes
            )
            ForumInteractions(
                initialLikes = 2000,
                initialComments = 500,
                initialIsLiked = false,
                initialIsSaved = false
            )
            LastMessageForum(
                userAvatar = userAvatar,
                message = message
            )
        }
    }
}

@Preview
@Composable
fun ForumItemPreview() {
    ForumItem(
        title = "Pós Operatório",
        subtitle = "Como foi seu pós operatório?",
        backgroundImageRes = R.drawable.img_defaul,
        userAvatar = R.drawable.avatar_1,
        message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit..."
    )
}