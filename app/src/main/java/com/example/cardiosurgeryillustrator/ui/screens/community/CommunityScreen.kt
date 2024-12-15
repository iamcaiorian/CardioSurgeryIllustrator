package com.example.cardiosurgeryillustrator.ui.screens.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.patient.community.CommunityTopBar
import com.example.cardiosurgeryillustrator.ui.components.patient.community.filter.CommunityCategoryFilterChipList
import com.example.cardiosurgeryillustrator.ui.components.patient.community.filter.CommunityFilterChipView
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.ForumItem

@Composable
fun CommunityScreen(
    avatarPainter: Painter,
    onSelectedCategoryChanged: (CommunityFilterChipView) -> Unit,
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
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CommunityTopBar(
            avatarPainter = avatarPainter
        )

        CommunityCategoryFilterChipList(
            onSelectedCategoryChanged = onSelectedCategoryChanged
        )

        ForumItem(
            title = title,
            subtitle = subtitle,
            backgroundImageRes = backgroundImageRes,
            likes = likes,
            comments = comments,
            isLiked = isLiked,
            isSaved = isSaved,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onSaveClick = onSaveClick,
            userAvatar = userAvatar,
            message = message
        )
    }
}

@Preview
@Composable
private fun CommunityScreenPreview() {
    CommunityScreen(
        avatarPainter = painterResource(id = R.drawable.avatar_1),
        onSelectedCategoryChanged = { /* Ação ao selecionar */ },
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
        onSaveClick = {},
        modifier = Modifier.fillMaxWidth()
    )
}