package com.example.cardiosurgeryillustrator.ui.screens.patient.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.patient.community.CommunityTopBar
import com.example.cardiosurgeryillustrator.ui.components.patient.community.filter.CommunityCategoryFilterChipList
import com.example.cardiosurgeryillustrator.ui.components.patient.community.filter.CommunityFilterChipView
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.ForumItem
import com.example.cardiosurgeryillustrator.ui.components.patient.community.forum.NewForumDialog
import com.example.cardiosurgeryillustrator.ui.theme.Zinc300
import com.example.cardiosurgeryillustrator.view_models.patient.community.CommunityViewModel
import com.example.cardiosurgeryillustrator.view_models.patient.community.CommunityViewModelFactory

@Composable
fun CommunityScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    communityViewModel: CommunityViewModel = viewModel(factory = CommunityViewModelFactory(
        ForumRepository(),
        PatientRepository(),
        LocalContext.current
    )),
) {
    var searchText by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf(CommunityFilterChipView.POPULARES) }
    var showNewForumDialog by remember { mutableStateOf(false) }

    val allForums by communityViewModel.forums.collectAsState()
    val savedForums = remember { mutableStateListOf<String>() }

    val filteredForums = remember(selectedFilter, allForums, searchText) {
        when (selectedFilter) {
            CommunityFilterChipView.POPULARES -> allForums.sortedByDescending { it.likes }
            CommunityFilterChipView.SALVOS -> allForums.filter { it.isFavorite }
        }.filter { forum ->
            forum.title.contains(searchText, ignoreCase = true) ||
                    forum.theme.contains(searchText, ignoreCase = true)
        }
    }

    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CommunityTopBar(
            avatarPainter = painterResource(id = R.drawable.avatar_1),
            searchQuery = searchText,
            onSearchQueryChanged = { searchText = it }
        )

        CommunityCategoryFilterChipList(
            onSelectedCategoryChanged = { category ->
                selectedFilter = category
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredForums) { forum ->
                ForumItem(
                    forum = forum,
                    navController = navController,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )

                HorizontalDivider(
                    color = Zinc300,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        StandardButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            onClick = { showNewForumDialog = true },
            text = "Novo Fórum",
            iconRes = R.drawable.ic_plus
        )
    }

    if (showNewForumDialog) {
        NewForumDialog(
            onDismiss = { showNewForumDialog = false },
            onConfirm = { theme, title ->
                communityViewModel.createNewForum(theme, title)
                showNewForumDialog = false
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommunityScreen() {
    CommunityScreen(
        navController = rememberNavController()
    )
}