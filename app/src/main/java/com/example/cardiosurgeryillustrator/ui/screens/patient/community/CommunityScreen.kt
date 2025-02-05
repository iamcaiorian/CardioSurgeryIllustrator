package com.example.cardiosurgeryillustrator.ui.screens.patient.community

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.patient.community.CommunityTopBar
import com.example.cardiosurgeryillustrator.ui.components.patient.community.filter.CommunityCategoryFilterChipList
import com.example.cardiosurgeryillustrator.ui.components.patient.community.filter.CommunityFilterChipView
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.ForumItem

@Composable
fun CommunityScreen(
    modifier: Modifier = Modifier,
    avatarPainter: Painter? = null,
    onSelectedCategoryChanged: ((CommunityFilterChipView) -> Unit)? = null,
    title: String = "",
    subtitle: String = "",
    backgroundImageRes: Int? = null,
    userAvatar: Int? = null,
    message: String = "",
    navController: NavController
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        avatarPainter?.let{
            CommunityTopBar(
                avatarPainter = avatarPainter
            )
        }


        onSelectedCategoryChanged?.let { callback ->
            CommunityCategoryFilterChipList(onSelectedCategoryChanged = callback)
        }

        // Verificar null para backgroundImageRes e userAvatar dentro do ForumItem
        ForumItem(
            title = title,
            subtitle = subtitle,
            backgroundImageRes = backgroundImageRes ?: R.drawable.img_defaul,
            userAvatar = userAvatar ?: R.drawable.avatar_1,
            navController = navController,
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
        userAvatar = R.drawable.avatar_1,
        message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
        modifier = Modifier.fillMaxWidth(),
        navController = rememberNavController()
    )
}