package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cardiosurgeryillustrator.models.mock.mockQuestions
import com.example.cardiosurgeryillustrator.ui.screens.authentication.LoginScreen
import com.example.cardiosurgeryillustrator.ui.screens.authentication.RegisterScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.notification.HabitDetailScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.notification.NotificationSettingsScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.form.CardioForm
import com.example.cardiosurgeryillustrator.ui.screens.welcome.ChooseUserScreen
import com.example.cardiosurgeryillustrator.ui.screens.welcome.WelcomeScreen
import com.example.cardiosurgeryillustrator.ui.view_models.student.settings.SettingsViewModel

sealed class AppScreen(val route: String) {
    object LoginFlow : AppScreen("login_flow_graph")
    object WelcomeFlow : AppScreen("welcome_flow_graph")
    object StudentFlow : AppScreen("student_flow_graph")
    object PatientFlow : AppScreen("patient_flow_graph")
    object AdminFlow : AppScreen("admin_flow_graph")
}

sealed class LoginScreen(val route: String) {
    object Login : LoginScreen("login_screen")
    object Register : LoginScreen("register_screen")
}

sealed class WelcomeScreen(val route: String) {
    object Welcome : WelcomeScreen("welcome_screen")
    object ChooseUser : WelcomeScreen("choose_user_screen")
}

sealed class FormScreen(val route: String) {
    object Form : FormScreen("form_screen")
}

@ExperimentalMaterial3Api
@Composable
fun NavGraph(
    isAuthenticated: Boolean,
    onLogin: () -> Unit,
    onLogout: () -> Unit,
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
            startDestination = startDestination
        ) {
            // Welcome Flow
            navigation(
                startDestination = WelcomeScreen.Welcome.route,
                route = AppScreen.WelcomeFlow.route
            ) {
                composable(WelcomeScreen.Welcome.route) {
                    WelcomeScreen(
                        onNavigateToChooseUser = {
                            navController.navigate(WelcomeScreen.ChooseUser.route)
                        }
                    )
                }
                composable(WelcomeScreen.ChooseUser.route) {
                    ChooseUserScreen(
                        onNavigateToStudent = {
                            navController.navigate(AppScreen.LoginFlow.route) {
                                popUpTo(WelcomeScreen.Welcome.route) { inclusive = true }
                            }
                        },
                        onNavigateToPatient = {
                            navController.navigate(FormScreen.Form.route) {
                                popUpTo(WelcomeScreen.Welcome.route) { inclusive = true }
                            }
                        },
                        onNavigateToAdmin = {
                            navController.navigate(AppScreen.AdminFlow.route) {
                                popUpTo(WelcomeScreen.Welcome.route) { inclusive = true }
                            }
                        }
                    )
                }
            }

            // Form Flow
            composable(FormScreen.Form.route) {
                Scaffold { innerPadding ->
                    CardioForm(
                        onNavigateToHome = {
                            navController.navigate(AppScreen.PatientFlow.route) {
                                popUpTo(WelcomeScreen.Welcome.route) { inclusive = true }
                            }
                        },
                        onBack = { navController.navigate(WelcomeScreen.ChooseUser.route) },
                        questionsList = mockQuestions,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

            // Login Flow
            navigation(startDestination = LoginScreen.Login.route, route = AppScreen.LoginFlow.route) {
                composable(LoginScreen.Login.route) {
                    LoginScreen(
                        onLoginClick = { _, _ ->
                            onLogin()
                            navController.navigate(AppScreen.StudentFlow.route) {
                                popUpTo(AppScreen.LoginFlow.route) { inclusive = true }
                            }
                        },
                        onForgotPasswordClick = { },
                        onRegisterClick = { navController.navigate(LoginScreen.Register.route) }
                    )
                }
                composable(LoginScreen.Register.route) {
                    RegisterScreen(
                        onRegisterClick = { _, _ ->
                            navController.navigate(LoginScreen.Login.route) {
                                popUpTo(LoginScreen.Login.route)
                            }
                        }
                    )
                }
            }

            // Student Flow
            composable(AppScreen.StudentFlow.route) {
                StudentNavHost(
                    onLogout = {
                        onLogout()
                        navController.navigate(AppScreen.LoginFlow.route) {
                            popUpTo(AppScreen.StudentFlow.route) { inclusive = true }
                        }
                    }
                )
            }

            // Habit Detail Screen
            composable(
                route = "habit_detail/{title}/{description}",
                arguments = listOf(
                    navArgument("title") { type = NavType.StringType },
                    navArgument("description") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: "Detalhes"
                val description = backStackEntry.arguments?.getString("description") ?: "Sem descrição disponível."
                HabitDetailScreen(
                    title = title,
                    description = description,
                    onBackClick = {
                        navController.navigate(AppScreen.StudentFlow.route) {
                            popUpTo(AppScreen.StudentFlow.route) { inclusive = true }
                        }
                    }
                )
            }

            // Patient Flow
            composable(AppScreen.PatientFlow.route) {
                PatientNavHost()
            }

            composable(AppScreen.AdminFlow.route) {
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
