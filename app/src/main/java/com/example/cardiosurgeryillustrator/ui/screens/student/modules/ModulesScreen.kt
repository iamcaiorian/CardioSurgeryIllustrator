package com.example.cardiosurgeryillustrator.ui.screens.student.modules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.navigation.SubjectAction
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import com.example.cardiosurgeryillustrator.ui.components.student.modules.ModuleCardList
import com.example.cardiosurgeryillustrator.ui.components.student.modules.TopBarModules
import com.example.cardiosurgeryillustrator.utils.module.makeModulesListUtil
import com.example.cardiosurgeryillustrator.view_models.student.module.ModulesViewModel
import com.example.cardiosurgeryillustrator.view_models.student.module.ModulesViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModulesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    subjectId: String,
    onNavigateBack: () -> Unit,
) {
    val moduleRepository = ModuleRepository()
    val viewModel: ModulesViewModel = viewModel(
        factory = ModulesViewModelFactory(moduleRepository)
    )

    var isLoading by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.getAllModulesBySubjectId(subjectId)
        isLoading = false
    }


    val modulesResponse = viewModel.modules
    val modules = makeModulesListUtil(modulesResponse)

    Scaffold(
        topBar = {
            TopBarModules(
                title = "Módulos",
                onNavigateBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        ModuleCardList(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            modulesList = modules,

            // NO NAV CONTROLLER A SEGUIR DEVE IR DIRETO PRA QUIZ, PASSANDO module.quiz.id e
            // tratar quiz inexistente, e alterar tambem no studentnavhost

            onModuleClick = { module ->
                navController.navigate("${SubjectAction.Study.route}/${module.id}")
            }
        )
    }
}

@Preview
@Composable
private fun ModulesScreenPreview() {
    ModulesScreen(
        navController = rememberNavController(),
        onNavigateBack = {},
        subjectId = ""
    )

}