package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.mock.mockModules
import com.example.cardiosurgeryillustrator.ui.components.admin.admin.BottomBarAdmin
import com.example.cardiosurgeryillustrator.ui.components.student.student.BottomBarStudent
import com.example.cardiosurgeryillustrator.ui.components.topBar.StandardTopBar
import com.example.cardiosurgeryillustrator.ui.screens.admin.admin_modules.AdminModulesScreen
import com.example.cardiosurgeryillustrator.ui.screens.admin.create_module.CreateModuleScreen
import com.example.cardiosurgeryillustrator.ui.screens.admin.faq.FAQScreen
import com.example.cardiosurgeryillustrator.ui.screens.admin.home.HomeAdminScreen
import com.example.cardiosurgeryillustrator.ui.screens.admin.login.LoginAdminScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.student.HomeStudentScreen

sealed class LoginAdminFlow(val route: String) {
    object Login : LoginAdminFlow("login_admin")
}

sealed class BottomBarAdminAction(
    val route: String,
    val icon: @Composable () -> Unit,
    val description: String
) {
    object Home : BottomBarAdminAction(
        route = "home",
        icon = { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = "Home") },
        description = "Home"
    )

    object Quiz : BottomBarAdminAction(
        route = "quiz",
        icon = {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.ic_question),
                contentDescription = "Subject",
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .aspectRatio(1f)
            )
        },
        description = "Quiz"
    )

    object Modules : BottomBarAdminAction(
        route = "subject",
        icon = {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.ic_note),
                contentDescription = "Subject",
                modifier = Modifier
                    .height(24.dp)
                    .width(20.dp)
                    .aspectRatio(1f)
            )
        },
        description = "Módulos"
    )

    object FAQ : BottomBarAdminAction(
        route = "faq",
        icon = { androidx.compose.material3.Icon(Icons.Default.Info, contentDescription = "FAQ") },

        description = "FAQ"
    )
}

sealed class AdminAction(val route: String) {
    data object CreateModule : AdminAction("create_module")
}

@Composable
@ExperimentalMaterial3Api
fun AdminNavHost() {
    val adminNavController = rememberNavController()

    NavHost(
        navController = adminNavController,
        startDestination = LoginAdminFlow.Login.route
    ) {
        composable(LoginAdminFlow.Login.route) {
            Scaffold { innerPadding ->
                LoginAdminScreen(
                    modifier = Modifier.padding(innerPadding),
                    onLoginClick = { _, _ ->
                        adminNavController.navigate(BottomBarAdminAction.Home.route) {
                            popUpTo(AppScreen.LoginFlow.route) { inclusive = true }
                        }
                    },
                    onForgotPasswordClick = { },
                )
            }

        }

        composable(BottomBarAdminAction.Home.route) {
            Scaffold(
                bottomBar = { BottomBarAdmin(navController = adminNavController) }
            ) { innerPadding ->
                HomeAdminScreen(
                    navController = adminNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable(BottomBarAdminAction.Modules.route) {
            Scaffold(
                bottomBar = { BottomBarAdmin(navController = adminNavController) },
                topBar = {
                    StandardTopBar(
                        modifier = Modifier,
                        onNavigateBack = { adminNavController.popBackStack() },
                        title = "Módulos"
                    )
                }
            ) { innerPadding ->
                AdminModulesScreen(
                    modifier = Modifier.padding(innerPadding),
                    onNavigateToCreateModule = { adminNavController.navigate(AdminAction.CreateModule.route) }
                )
            }
        }

        composable(AdminAction.CreateModule.route) {
            Scaffold(
                bottomBar = { BottomBarAdmin(navController = adminNavController) }
            ) { innerPadding ->
                CreateModuleScreen(
                    modifier = Modifier.padding(innerPadding),
                    onNavigateBack = { adminNavController.popBackStack() },
                    onNavigateToModules = { adminNavController.navigate(BottomBarAdminAction.Modules.route) }
                )
            }
        }

        composable(BottomBarAdminAction.Quiz.route) {
            Scaffold(
                bottomBar = { BottomBarAdmin(navController = adminNavController) }
            ) { innerPadding ->
                // tela de quiz
            }
        }

        composable(BottomBarAdminAction.FAQ.route) {
            Scaffold(
                bottomBar = { BottomBarAdmin(navController = adminNavController) },
                topBar = {
                    StandardTopBar(
                        modifier = Modifier,
                        onNavigateBack = { adminNavController.popBackStack() },
                        title = "FAQ"
                    )
                }
            ) { innerPadding ->
                FAQScreen(
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}
