package com.example.cardiosurgeryillustrator.ui.screens.community

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.patient.community.filter.CommunityCategoryFilterChipList
import com.example.cardiosurgeryillustrator.ui.components.patient.community.filter.CommunityFilterChipView
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.ForumBottomBar
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.ForumInteractions
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.ForumTopBar
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.LastMessageForum

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForumScreen(
    onSelectedCategoryChanged: (CommunityFilterChipView) -> Unit,
    userAvatar: Int,
    message: String,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        bottomBar = {
            ForumBottomBar(modifier = Modifier)
        },
        content = {
            Column(
                modifier = modifier
                    .background(Color.White)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ForumTopBar(
                    title = "Pós Operatório",
                    subtitle = "Como foi seu pós operatório?",
                    backgroundImageRes = R.drawable.img_defaul
                )

                ForumInteractions(
                    initialLikes = 2000,
                    initialComments = 500,
                    initialIsLiked = false,
                    initialIsSaved = false
                )

                CommunityCategoryFilterChipList(
                    onSelectedCategoryChanged = onSelectedCategoryChanged
                )

                Spacer(modifier = Modifier.height(12.dp))

                LastMessageForum(
                    userAvatar = userAvatar,
                    message = message
                )
            }
        }
    )
}

@Preview
@Composable
private fun ForumScreenPreview() {
    ForumScreen(
        onSelectedCategoryChanged = { /* Ação ao selecionar */ },
        userAvatar = R.drawable.avatar_1,
        message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
        modifier = Modifier.fillMaxWidth()
    )
}