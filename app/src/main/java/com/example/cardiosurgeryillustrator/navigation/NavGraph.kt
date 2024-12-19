package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.mock.mockQuestions
import com.example.cardiosurgeryillustrator.ui.screens.authentication.LoginScreen
import com.example.cardiosurgeryillustrator.ui.screens.authentication.RegisterScreen
import com.example.cardiosurgeryillustrator.ui.screens.community.ForumScreen
import com.example.cardiosurgeryillustrator.ui.screens.patient.form.CardioForm
import com.example.cardiosurgeryillustrator.ui.screens.welcome.ChooseUserScreen
import com.example.cardiosurgeryillustrator.ui.screens.welcome.WelcomeScreen

sealed class AppScreen(val route: String) {
    object LoginFlow : AppScreen("login_flow_graph")
    object WelcomeFlow : WelcomeScreen("welcome_flow_graph")
    object StudentFlow : AppScreen("student_flow_graph")
    object PatientFlow : AppScreen("patient_flow_graph")
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
fun AppNavGraph(
    isAuthenticated: Boolean,
    onLogin: () -> Unit,
    onLogout: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (isAuthenticated) AppScreen.StudentFlow.route else AppScreen.WelcomeFlow.route
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
                    }
                )
            }
        }

        //Form Flow
        composable(FormScreen.Form.route) {
            CardioForm(
                onNavigateToHome = {
                    navController.navigate(AppScreen.PatientFlow.route) {
                        popUpTo(WelcomeScreen.Welcome.route) { inclusive = true }
                    }
                },
                onBack = {   navController.navigate(WelcomeScreen.ChooseUser.route) },
                questionsList = mockQuestions
            )
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

        //Patient Flow
        composable(AppScreen.PatientFlow.route) {
            PatientNavHost()
        }
    }
}