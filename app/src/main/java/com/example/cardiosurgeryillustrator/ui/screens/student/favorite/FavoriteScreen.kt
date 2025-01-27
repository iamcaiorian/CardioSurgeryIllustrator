package com.example.cardiosurgeryillustrator.ui.screens.student.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.mock.mockModules
import com.example.cardiosurgeryillustrator.navigation.SubjectAction
import com.example.cardiosurgeryillustrator.ui.components.student.modules.ModuleCardList

@Composable
fun FavoriteScreen(modifier: Modifier = Modifier, navController: NavController, modulesList: List<Module>) {

    val modulesState = remember { mutableStateListOf(*modulesList.toTypedArray()) }

    val favoriteModules = modulesState.filter { it.isFavorite }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (favoriteModules.isEmpty()) {
            Text(
                text = "Você ainda não tem módulos favoritos.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        } else {
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


@Preview
@Composable
private fun FavoriteScreenPreview() {
    FavoriteScreen(
        navController = rememberNavController(),
        modulesList = mockModules,
        modifier = Modifier.padding(16.dp)
    )
}