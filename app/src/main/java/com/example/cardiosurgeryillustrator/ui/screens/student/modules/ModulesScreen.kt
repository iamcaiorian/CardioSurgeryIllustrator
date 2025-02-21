package com.example.cardiosurgeryillustrator.ui.screens.student.modules

import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.cardiosurgeryillustrator.utils.module.makeModulesListUtil
import com.example.cardiosurgeryillustrator.view_models.student.module.ModulesViewModel
import com.example.cardiosurgeryillustrator.view_models.student.module.ModulesViewModelFactory

@Composable
fun ModulesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    subjectId: String,
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


    ModuleCardList(
        modifier = modifier
            .fillMaxSize(),
        modulesList = modules,

        onModuleClick = { module ->
            navController.navigate("${SubjectAction.Study.route}/${module.id}")
        }
    )

}

@Preview
@Composable
private fun ModulesScreenPreview() {
    ModulesScreen(
        navController = rememberNavController(),
        subjectId = ""
    )

}