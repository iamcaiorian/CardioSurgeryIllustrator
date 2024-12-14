package com.example.cardiosurgeryillustrator.ui.screens.subject

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.models.mock.mockSubjects
import com.example.cardiosurgeryillustrator.navigation.SubjectAction
import com.example.cardiosurgeryillustrator.ui.components.modules.SubjectCardList
import com.example.cardiosurgeryillustrator.ui.components.modules.TopBarModules

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarModules(
                title = "Assuntos",
                navController = navController,
                onHelpClick = { },
                onSettingsClick = { },
                onNavigateBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        SubjectCardList(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            subjectList = mockSubjects,
            onSubjectClick = { subject ->
                navController.navigate("${SubjectAction.Modules.route}/${subject.id}")
            }
        )
    }
}

@Preview
@Composable
private fun HomeModulesScreenPreview() {
    SubjectsScreen(
        modifier = Modifier,
        navController = rememberNavController(),
        onNavigateBack = {})
}