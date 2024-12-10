package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.ui.components.topBar.TopBarModules
import com.example.cardiosurgeryillustrator.ui.screens.modules.HomeModulesScreen

sealed class ModulesScreens(val route: String) {
    object Home : LoginScreen("modules_home")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModulesNavHost(onNavigateBack: () -> Unit) {
    val modulesNavController = rememberNavController()

    Scaffold(
        topBar = {
            TopBarModules(onHelpClick = {}, onSettingsClick = {})
        },
    ) { innerPadding ->
        NavHost(
            navController = modulesNavController,
            startDestination = ModulesScreens.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ModulesScreens.Home.route) {
                HomeModulesScreen(navController = modulesNavController)
            }

        }
    }
}