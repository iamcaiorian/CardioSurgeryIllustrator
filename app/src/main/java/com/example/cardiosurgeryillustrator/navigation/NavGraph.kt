package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.cardiosurgeryillustrator.ui.screens.login.LoginScreen
import com.example.cardiosurgeryillustrator.ui.screens.login.RegisterScreen

sealed class AppScreen(val route: String) {
    object LoginFlow : AppScreen("login_flow_graph")
    object StudentFlow : AppScreen("student_flow_graph")
}

sealed class LoginScreen(val route: String) {
    object Login : LoginScreen("login_screen")
    object Register : LoginScreen("register_screen")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(
    isAuthenticated: Boolean,
    onLogin: () -> Unit,
    onLogout: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (isAuthenticated) AppScreen.StudentFlow.route else AppScreen.LoginFlow.route
    ) {
        // Login navigation graph
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
                        navController.navigate(LoginScreen.Login.route) { popUpTo(LoginScreen.Login.route) }
                    }
                )
            }
        }

        // Student navigation graph
        composable(AppScreen.StudentFlow.route) {
            StudentNavHost(onLogout = onLogout)
        }
    }
}
