package com.example.cardiosurgeryillustrator.ui.components.patient.community.forum

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R


@Composable
fun ForumItem(
    title: String,
    subtitle: String,
    backgroundImageRes: Int,
    likes: Int,
    comments: Int,
    isLiked: Boolean,
    isSaved: Boolean,
    userAvatar: Int,
    message: String,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onSaveClick: () -> Unit,
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
                likes = likes,
                comments = comments,
                isLiked = isLiked,
                isSaved = isSaved,
                onLikeClick = onLikeClick,
                onCommentClick = onCommentClick,
                onSaveClick = onSaveClick
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
        likes = 2000,
        comments = 500,
        isLiked = false,
        isSaved = true,
        userAvatar = R.drawable.avatar_1,
        message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
        onLikeClick = {},
        onCommentClick = {},
        onSaveClick = {}
    )
}