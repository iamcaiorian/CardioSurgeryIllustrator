package com.example.cardiosurgeryillustrator.ui.screens.patient.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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
import com.example.cardiosurgeryillustrator.view_models.patient.community.PatientViewModel
import com.example.cardiosurgeryillustrator.view_models.patient.community.PatientViewModelFactory

@Composable
fun CommunityScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    communityViewModel: CommunityViewModel = viewModel(factory = CommunityViewModelFactory(ForumRepository())),
    patientViewModel: PatientViewModel = viewModel(factory = PatientViewModelFactory(PatientRepository()))
) {
    var searchText by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf(CommunityFilterChipView.POPULARES) }
    var showNewForumDialog by remember { mutableStateOf(false) }

    val allTopics by communityViewModel.topics.collectAsState(emptyList())
    val savedForums by patientViewModel.savedForums.collectAsState(emptyList())

    val filteredTopics = remember(selectedFilter, allTopics, savedForums, searchText) {
        when (selectedFilter) {
            CommunityFilterChipView.POPULARES -> allTopics.sortedByDescending { it.likes }
            CommunityFilterChipView.SALVOS -> allTopics.filter { it.id in savedForums }
        }.filter { topic ->
            topic.title.contains(searchText, ignoreCase = true) ||
                    topic.theme.contains(searchText, ignoreCase = true)
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
            items(filteredTopics) { topic ->
                ForumItem(
                    topic = topic,
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
                communityViewModel.createNewForum(theme, title, userId = "1")
                showNewForumDialog = false
            }
        )
    }
}
