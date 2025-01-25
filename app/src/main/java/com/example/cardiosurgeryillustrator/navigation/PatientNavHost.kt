package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.foundation.layout.fillMaxWidth

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
import com.example.cardiosurgeryillustrator.models.mock.mockClinics
import com.example.cardiosurgeryillustrator.models.mock.mockInfoText
import com.example.cardiosurgeryillustrator.ui.components.patient.BottomBarPacient
import com.example.cardiosurgeryillustrator.ui.screens.patient.home.ArteryDetailsScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.AssistantScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.home.HomePacientScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.MoreScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.appointment_schedule.AppointmentScheduleScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.appointment_schedule.NewAppointmentScheduleScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.community.CommunityScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.community.ForumScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.nearby_clinics.NearbyClinics


@ExperimentalMaterial3Api
@Composable
fun PatientNavHost() {
    val pacientNavController = rememberNavController()

    NavHost(
        navController = pacientNavController,
        startDestination = BottomBarPacientAction.HomePacient.route,
        modifier = Modifier
    ) {
        // Tela Home
        composable(BottomBarPacientAction.HomePacient.route) {
            Scaffold(
                bottomBar = { BottomBarPacient(navController = pacientNavController) }
            ) { innerPadding ->
                HomePacientScreen(
                    navController = pacientNavController,
                    modifier = Modifier.padding(innerPadding),
                    infoTextList = mockInfoText
                )
            }
        }

        // Tela Comunidade
        composable(BottomBarPacientAction.Community.route) {
            Scaffold(
                bottomBar = { BottomBarPacient(navController = pacientNavController) }
            ) { innerPadding ->
                CommunityScreen(
                    avatarPainter = painterResource(id = R.drawable.avatar_1),
                    onSelectedCategoryChanged = { /* Ação ao selecionar */ },
                    title = "Pós Operatório",
                    subtitle = "Como foi seu pós operatório?",
                    backgroundImageRes = R.drawable.img_defaul,
                    userAvatar = R.drawable.avatar_1,
                    message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                    navController = pacientNavController
                )
            }
        }

        // Tela Fórum
        composable("forum_screen") {
            Scaffold() { innerPadding ->
                ForumScreen(
                    onSelectedCategoryChanged = { /* Ação ao selecionar */ },
                    userAvatar = R.drawable.avatar_1,
                    message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                )
            }
        }

        // Tela Assistente
        composable(BottomBarPacientAction.Assistant.route) {
            Scaffold(
                bottomBar = { BottomBarPacient(navController = pacientNavController) }
            ) { innerPadding ->
                AssistantScreen(
                    navController = pacientNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        // Tela Mais
        composable(BottomBarPacientAction.More.route) {
            Scaffold(
                bottomBar = { BottomBarPacient(navController = pacientNavController) }
            ) { innerPadding ->
                MoreScreen(
                    navController = pacientNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        // Detalhes da Artéria
        composable(
            route = "artery_details/{arteryName}",
            arguments = listOf(navArgument("arteryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val arteryName = backStackEntry.arguments?.getString("arteryName") ?: ""
            Scaffold() { innerPadding ->
                ArteryDetailsScreen(
                    navController = pacientNavController,
                    arteryName = arteryName,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable("nearby_clinics") {
            Scaffold { innerPadding ->
                NearbyClinics(
                    navController = pacientNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable("appointment_schedule_screen") {
            Scaffold { innerPadding ->
                AppointmentScheduleScreen(
                    navController = pacientNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
        composable("new_appointment_screen") {
            Scaffold { innerPadding ->
                NewAppointmentScheduleScreen(
                    navController = pacientNavController,
                    mockClinics,
                    modifier = Modifier.padding(innerPadding)
                )
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
