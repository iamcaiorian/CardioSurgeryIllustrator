package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cardiosurgeryillustrator.ui.screens.login.LoginScreen
import com.example.cardiosurgeryillustrator.ui.screens.login.RegisterScreen

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")  // Nova rota adicionada
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(
                onLoginClick = { _, _ -> /* Handle login */ },
                onForgotPasswordClick = { /* Handle forgot password */ },
                onRegisterClick = { navController.navigate(Screen.Register.route) } // Navega para a tela de cadastro
            )
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(
                onRegisterClick = { _, _ -> /* Handle registration */ }
            )
        }
    }
}
