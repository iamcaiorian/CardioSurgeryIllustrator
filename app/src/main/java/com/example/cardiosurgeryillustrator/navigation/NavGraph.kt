package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cardiosurgeryillustrator.ui.screens.student.notification.HabitDetailScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.notification.NotificationSettingsScreen
import com.example.cardiosurgeryillustrator.ui.screens.welcome.ChooseUserScreen
import com.example.cardiosurgeryillustrator.ui.screens.welcome.WelcomeScreen
import com.example.cardiosurgeryillustrator.view_models.student.settings.SettingsViewModel

sealed class AppFlow(val route: String) {
    object WelcomeFlow : AppFlow("welcome_flow_graph")
    object StudentFlow : AppFlow("student_flow_graph")
    object PatientFlow : AppFlow("patient_flow_graph")
    object AdminFlow : AppFlow("admin_flow_graph")
}

sealed class WelcomeFlow(val route: String) {
    object Welcome : WelcomeFlow("welcome_screen")
    object ChooseUser : WelcomeFlow("choose_user_screen")
}


@ExperimentalMaterial3Api
@Composable
fun NavGraph(
    startDestination: String
) {
    val settingsViewModel = SettingsViewModel(LocalContext.current)
    val navController = rememberNavController()

    val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()

    MaterialTheme(
        colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            enterTransition = { slideInHorizontally() },
            exitTransition = { slideOutHorizontally() },
        ) {
            // Welcome Flow
            navigation(
                startDestination = WelcomeFlow.Welcome.route,
                route = AppFlow.WelcomeFlow.route
            ) {
                composable(WelcomeFlow.Welcome.route) {
                    WelcomeScreen(
                        onNavigateToChooseUser = {
                            navController.navigate(WelcomeFlow.ChooseUser.route)
                        }
                    )
                }
                composable(WelcomeFlow.ChooseUser.route) {
                    ChooseUserScreen(
                        onNavigateToStudent = {
                            navController.navigate(AppFlow.StudentFlow.route) {
                                popUpTo(WelcomeFlow.Welcome.route) { inclusive = true }
                            }
                        },
                        onNavigateToPatient = {
                            navController.navigate(AppFlow.PatientFlow.route) {
                                popUpTo(WelcomeFlow.Welcome.route) { inclusive = true }
                            }
                        },
                        onNavigateToAdmin = {
                            navController.navigate(AppFlow.AdminFlow.route) {
                                popUpTo(WelcomeFlow.Welcome.route) { inclusive = true }
                            }
                        }
                    )
                }
            }


            composable(AppFlow.StudentFlow.route) {
                StudentNavHost()
            }

            composable(AppFlow.PatientFlow.route) {
                PatientNavHost()
            }

            composable(AppFlow.AdminFlow.route) {
                AdminNavHost()
            }


            // Notifications
            composable("notifications") {
                NotificationSettingsScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
