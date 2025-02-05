package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import com.example.cardiosurgeryillustrator.models.mock.patient.mockClinics
import com.example.cardiosurgeryillustrator.models.mock.patient.mockInfoText
import com.example.cardiosurgeryillustrator.models.mock.student.mockQuestions
import com.example.cardiosurgeryillustrator.ui.components.patient.BottomBarPacient
import com.example.cardiosurgeryillustrator.ui.components.topBar.StandardTopBar
import com.example.cardiosurgeryillustrator.ui.screens.patient.home.ArteryDetailsScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.assistant.AssistantScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.home.HomePacientScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.more.MoreScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.appointment_schedule.AppointmentScheduleScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.appointment_schedule.NewAppointmentScheduleScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.community.CommunityScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.community.ForumScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.faq.PatientFAQScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.form.CardioForm
import com.example.cardiosurgeryillustrator.ui.screens.patient.nearby_clinics.NearbyClinics
import com.example.cardiosurgeryillustrator.ui.screens.student.notification.HabitDetailScreen

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

sealed class FormFlow(val route: String) {
    object Form : FormFlow("form_screen")
}

@ExperimentalMaterial3Api
@Composable
fun PatientNavHost() {
    val patientNavController = rememberNavController()

    val bottomBarRoutes = listOf(BottomBarPacientAction.HomePacient.route, BottomBarPacientAction.Community.route, BottomBarPacientAction.Assistant.route, BottomBarPacientAction.More.route)

    NavHost(
        navController = patientNavController,
        startDestination = FormFlow.Form.route,
        modifier = Modifier,
        enterTransition = {
            val fromIndex = bottomBarRoutes.indexOf(initialState.destination.route)
            val toIndex = bottomBarRoutes.indexOf(targetState.destination.route)
            if (toIndex > fromIndex) {
                // Navegação "para frente" (da direita para esquerda)
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            } else {
                // Navegação "para trás" (da esquerda para direita)
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            }
        },
        exitTransition = {
            val fromIndex = bottomBarRoutes.indexOf(initialState.destination.route)
            val toIndex = bottomBarRoutes.indexOf(targetState.destination.route)
            if (toIndex > fromIndex) {
                // Navegação "para frente" (saindo para a esquerda)
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            } else {
                // Navegação "para trás" (saindo para a direita)
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            }
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = 300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = 300)
            )
        }
    ) {

        // Form Flow
        composable(FormFlow.Form.route) {
            Scaffold { innerPadding ->
                CardioForm(
                    onNavigateToHome = {
                        patientNavController.navigate(BottomBarPacientAction.HomePacient.route) {
                            popUpTo(WelcomeFlow.Welcome.route) { inclusive = true }
                        }
                    },
                    onBack = { patientNavController.navigate(WelcomeFlow.ChooseUser.route) },
                    questionsList = mockQuestions,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable(
            route = "habit_detail/{title}/{description}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Detalhes"
            val description = backStackEntry.arguments?.getString("description")
                ?: "Sem descrição disponível."
            HabitDetailScreen(
                title = title,
                description = description,
                onBackClick = {
                    patientNavController.navigate(AppFlow.StudentFlow.route) {
                        popUpTo(AppFlow.StudentFlow.route) { inclusive = true }
                    }
                }
            )
        }

        // Tela Home
        composable(BottomBarPacientAction.HomePacient.route) {
            Scaffold(
                bottomBar = { BottomBarPacient(navController = patientNavController) }
            ) { innerPadding ->
                HomePacientScreen(
                    navController = patientNavController,
                    modifier = Modifier.padding(innerPadding),
                    infoTextList = mockInfoText
                )
            }
        }

        // Tela Comunidade
        composable(BottomBarPacientAction.Community.route) {
            Scaffold(
                bottomBar = { BottomBarPacient(navController = patientNavController) }
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
                    navController = patientNavController
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
                bottomBar = { BottomBarPacient(navController = patientNavController) }
            ) { innerPadding ->
                AssistantScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        // Tela Mais
        composable(BottomBarPacientAction.More.route) {
            Scaffold(
                bottomBar = { BottomBarPacient(navController = patientNavController) }
            ) { innerPadding ->
                MoreScreen(
                    navController = patientNavController,
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
                    navController = patientNavController,
                    arteryName = arteryName,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable("nearby_clinics") {
            Scaffold { innerPadding ->
                NearbyClinics(
                    navController = patientNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable("appointment_schedule_screen") {
            Scaffold { innerPadding ->
                AppointmentScheduleScreen(
                    navController = patientNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
        composable("new_appointment_screen") {
            Scaffold { innerPadding ->
                NewAppointmentScheduleScreen(
                    navController = patientNavController,
                    mockClinics,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable("faq") {
            Scaffold(
                topBar = {
                    StandardTopBar(
                        onNavigateBack = { patientNavController.popBackStack() },
                        title = "Perguntas Frequentes"
                    )
                }
            ) { innerPadding ->
                PatientFAQScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

