package com.example.cardiosurgeryillustrator.ui.screens.student.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.navigation.SubjectAction
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import com.example.cardiosurgeryillustrator.ui.components.student.modules.ModuleCardList
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.utils.module.makeModulesListUtil
import com.example.cardiosurgeryillustrator.view_models.student.module.ModulesViewModel
import com.example.cardiosurgeryillustrator.view_models.student.module.ModulesViewModelFactory


@Composable
fun FavoriteScreen(modifier: Modifier, navController: NavController) {

    val moduleRepository = ModuleRepository()
    val viewModel: ModulesViewModel = viewModel(
        factory = ModulesViewModelFactory(moduleRepository)
    )

    var isLoading by remember { mutableStateOf(true) }
    val modulesResponse = viewModel.modules


    LaunchedEffect(Unit) {
        viewModel.getAllModules()
    }

    LaunchedEffect(modulesResponse) {
        if (modulesResponse.isNotEmpty()) {
            isLoading = false
        }
    }


    val validModules = modulesResponse.filter { it.subjectId != null }

    val modules = makeModulesListUtil(validModules)

    val favoriteModules = modules.filter {it.isFavorite.value}

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Blue700)
                }
            }
            favoriteModules.isEmpty() -> {
                Text(
                    text = "Você ainda não tem módulos favoritos.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
            else -> {
                ModuleCardList(
                    modifier = modifier.padding(top = 16.dp),
                    modulesList = favoriteModules,
                    onModuleClick = { module ->
                        navController.navigate("${SubjectAction.Study.route}/${module.id}")
                    }
                )
            }
        }
    }

}




@Preview
@Composable
private fun FavoriteScreenPreview() {
    FavoriteScreen(
        navController = rememberNavController(),
        modifier = Modifier.padding(16.dp)
    )
}
