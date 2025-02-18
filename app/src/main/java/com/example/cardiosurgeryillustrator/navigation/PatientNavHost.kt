package com.example.cardiosurgeryillustrator.navigation

import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.mock.patient.mockClinics
import com.example.cardiosurgeryillustrator.models.mock.patient.mockQuestions
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
import com.example.cardiosurgeryillustrator.ui.screens.patient.settings.PatientSettingsScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.notification.NotificationSettingsScreen
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cardiosurgeryillustrator.repository.patient.community.CommentRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository
import com.example.cardiosurgeryillustrator.ui.screens.patient.home.LifeStyleScreen
import com.example.cardiosurgeryillustrator.view_models.patient.community.ForumViewModelFactory


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

sealed class MoreActionsFlow(val route: String) {
    object Settings : MoreActionsFlow("settings_patient")
}

@ExperimentalMaterial3Api
@Composable
fun PatientNavHost() {
    val patientNavController = rememberNavController()


    val uuidFlow = DataStoreUtils.readPatientUUID(LocalContext.current)
    val uuid = uuidFlow.collectAsStateWithLifecycle(initialValue = null)

    val startDestination = uuid.value?.let {
        BottomBarPacientAction.HomePacient.route
    } ?: FormFlow.Form.route

    val bottomBarRoutes = listOf(
        BottomBarPacientAction.HomePacient.route,
        BottomBarPacientAction.Community.route,
        BottomBarPacientAction.Assistant.route,
        BottomBarPacientAction.More.route
    )

    NavHost(
        navController = patientNavController,
        startDestination = startDestination,
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
                    navController = patientNavController,
                    questionsList = mockQuestions,
                    modifier = Modifier.padding(innerPadding),

                    )
            }
        }

        // Tela Home
        composable(BottomBarPacientAction.HomePacient.route) {
            Scaffold(
                bottomBar = { BottomBarPacient(navController = patientNavController) }
            ) { innerPadding ->
                HomePacientScreen(
                    navController = patientNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        // Tela Comunidade
        composable(BottomBarPacientAction.Community.route) {
            Scaffold(
                bottomBar = { BottomBarPacient(navController = patientNavController) }
            ) { innerPadding ->
                CommunityScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                    navController = patientNavController
                )
            }
        }

        // Tela Fórum
        composable(
            route = "forum_screen/{forumId}/{isFavorite}/{isLiked}",
            arguments = listOf(
                navArgument("forumId") { type = NavType.StringType },
                navArgument("isFavorite") { type = NavType.BoolType },
                navArgument("isLiked") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val forumId = backStackEntry.arguments?.getString("forumId")
            val isFavorite = backStackEntry.arguments?.getBoolean("isFavorite") ?: false
            val isLiked = backStackEntry.arguments?.getBoolean("isLiked") ?: false

            if (!forumId.isNullOrEmpty()) {
                ForumScreen(
                    navController = patientNavController,
                    forumId = forumId,
                    isFavorite = isFavorite,
                    isLiked = isLiked
                )
            } else {
                Log.e("Navigation", "Erro ao abrir a tela do fórum: ID do fórum vazio")
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
            Scaffold { innerPadding ->
                ArteryDetailsScreen(
                    navController = patientNavController,
                    arteryName = arteryName,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable("life_style") {
            Scaffold { innerPadding ->
                LifeStyleScreen(
                    navController = patientNavController,
                    modifier = Modifier.padding(innerPadding),
                    imc = 30f
                )
            }
        }

        //Clínicas próximas
        composable("nearby_clinics") {
            Scaffold { innerPadding ->
                NearbyClinics(
                    navController = patientNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        //Agendamento de consultas
        composable("appointment_schedule_screen") {
            Scaffold { innerPadding ->
                AppointmentScheduleScreen(
                    navController = patientNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        //Novo agendamento
        composable("new_appointment_screen") {
            Scaffold { innerPadding ->
                NewAppointmentScheduleScreen(
                    navController = patientNavController,
                    mockClinics,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        //Configurações
        composable(MoreActionsFlow.Settings.route) {
            Scaffold { innerPadding ->
                PatientSettingsScreen(
                    navController = patientNavController,
                    modifier = Modifier.padding(innerPadding),
                    onNavigateBack = { patientNavController.popBackStack() }
                )
            }
        }

        // Notifications
        composable("notifications") {
            NotificationSettingsScreen(
                onBackClick = { patientNavController.popBackStack() }
            )
        }

        //FAQ
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

