package com.example.cardiosurgeryillustrator.ui.screens.student.modules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.mock.mockModules
import com.example.cardiosurgeryillustrator.navigation.SubjectAction
import com.example.cardiosurgeryillustrator.ui.components.student.modules.ModuleCardList
import com.example.cardiosurgeryillustrator.ui.components.student.modules.TopBarModules

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModulesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    modulesList: List<Module>,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarModules(
                title = "Módulos",
                navController = navController,
                onHelpClick = { },
                onSettingsClick = { },
                onNavigateBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        ModuleCardList(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            modulesList = modulesList,
            onModuleClick = {module -> navController.navigate("${SubjectAction.ModulesVideo.route}/${module.id}")}
        )
    }
}

@Preview
@Composable
private fun ModulesScreenPreview() {
    ModulesScreen(
        navController = rememberNavController(),
        modulesList = mockModules,
        onNavigateBack = {})
}