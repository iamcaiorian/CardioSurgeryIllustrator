package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.patient.BottomBarPacient
import com.example.cardiosurgeryillustrator.ui.screens.patient.ArteryDetailsScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.AssistantScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.CommunityScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.HomePacientScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.MoreScreen

@ExperimentalMaterial3Api
@Composable
fun PatientNavHost() {
    val pacientNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBarPacient(navController = pacientNavController) }
    ) { innerPadding ->
        NavHost(
            navController = pacientNavController,
            startDestination = BottomBarPacientAction.HomePacient.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomBarPacientAction.HomePacient.route) {
                HomePacientScreen(navController = pacientNavController)
            }

            composable(BottomBarPacientAction.Community.route) {
                CommunityScreen(navController = pacientNavController)
            }

            composable(BottomBarPacientAction.Assistant.route) {
                AssistantScreen(navController = pacientNavController)
            }

            composable(BottomBarPacientAction.More.route) {
                MoreScreen(navController = pacientNavController)
            }

            composable(
                route = "artery_details/{arteryName}",
                arguments = listOf(navArgument("arteryName") { type = NavType.StringType })
            ) { backStackEntry ->
                val arteryName = backStackEntry.arguments?.getString("arteryName") ?: ""
                ArteryDetailsScreen(navController = pacientNavController, arteryName = arteryName)
            }
        }
    }
}

sealed class BottomBarPacientAction(
    val route: String,
    val icon: @Composable () -> Unit,
    val description: String
) {
    object HomePacient : BottomBarPacientAction(
        route = "home-pacient",
        icon = {
            androidx.compose.material3.Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = "Home Paciente"
            )
        },
        description = "Início"
    )

    object Community : BottomBarPacientAction(
        route = "community",
        icon = {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.ic_community),
                contentDescription = "Comunidade",
                modifier = Modifier.size(24.dp)
            )
        },
        description = "Comunidade"
    )

    object Assistant : BottomBarPacientAction(
        route = "assistant",
        icon = {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.ic_assistant),
                contentDescription = "Assistente",
                modifier = Modifier.size(24.dp)
            )
        },
        description = "Assistente"
    )

    object More : BottomBarPacientAction(
        route = "more",
        icon = {
            androidx.compose.material3.Icon(
                Icons.Default.MoreVert,
                contentDescription = "Mais"
            )
        },
        description = "Mais"
    )
}
