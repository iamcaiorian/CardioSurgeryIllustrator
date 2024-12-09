package com.example.cardiosurgeryillustrator.ui.components.student

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cardiosurgeryillustrator.navigation.TopBarStudentAction

@ExperimentalMaterial3Api
@Composable
fun TopBarStudent(navController: NavController) {
    val actions = listOf(
        TopBarStudentAction.Profile,
        TopBarStudentAction.Search,
        TopBarStudentAction.Settings
    )

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    TopAppBar(
        title = {
            Row {
                // Ícone do perfil e título alinhados à esquerda
                TopBarStudentAction.Profile.icon()
                Text(
                    text = "Estudante",
                    color = Color.Black
                )
            }
        },
        actions = {
            // Ícones da barra superior alinhados à direita
            actions.drop(1).forEach { action -> // Ignorar o primeiro (Profile)
                IconButton(
                    onClick = {
                        navController.navigate(action.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                ) {
                    action.icon()
                }
            }
        }
    )
}
