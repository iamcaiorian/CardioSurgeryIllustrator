package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cardiosurgeryillustrator.ui.components.student.BottomBarStudent
import com.example.cardiosurgeryillustrator.ui.components.student.TopBarStudent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.ui.screens.student.HomeStudentScreen

sealed class TopBarStudentAction(val route: String, val icon: @Composable () -> Unit, val description: String) {
    object Profile : TopBarStudentAction(
        route = "profile",
        icon = { androidx.compose.material3.Icon(Icons.Default.AccountCircle, contentDescription = "AccountCircle") },
        description = "Profile"
    )

    object Search : TopBarStudentAction(
        route = "search",
        icon = { androidx.compose.material3.Icon(Icons.Default.Search, contentDescription = "Search") },
        description = "Search"
    )

    object Settings : TopBarStudentAction(
        route = "settings",
        icon = { androidx.compose.material3.Icon(Icons.Default.Settings, contentDescription = "Settings") },
        description = "Settings"
    )
}

sealed class BottomBarStudentAction(val route: String, val icon: @Composable () -> Unit, val description: String) {
    object Home : BottomBarStudentAction(
        route = "home",
        icon = { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = "Home") },
        description = "Home"
    )

    object Modules : BottomBarStudentAction(
        route = "modules",
        // ---------------- TROCAR ICONE -----------------
        icon = { androidx.compose.material3.Icon(Icons.Default.Info, contentDescription = "Modules") },
        description = "Modules"
    )

    object Favorites : BottomBarStudentAction(
        route = "favorites",
        icon = { androidx.compose.material3.Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorite") },
        description = "Favorites"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentNavHost(onLogout: () -> Unit) {
    val studentNavController = rememberNavController()

    Scaffold(
        topBar = {
            TopBarStudent(navController = studentNavController)
        },
        bottomBar = {
            BottomBarStudent(navController = studentNavController)
        }
    ) { innerPadding ->
        NavHost(
            navController = studentNavController,
            startDestination = BottomBarStudentAction.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomBarStudentAction.Home.route) {
                HomeStudentScreen(navController = studentNavController)
            }
//            composable(BottomBarStudentAction.Modules.route) {
//                ModulesScreen(navController = studentNavController)
//            }
//            composable(BottomBarStudentAction.Favorites.route) {
//                FavoritesScreen(navController = studentNavController)
//            }
//            composable(TopBarStudentAction.Profile.route) {
//                ProfileScreen(navController = studentNavController)
//            }
//            composable(TopBarStudentAction.Search.route) {
//                SearchScreen(navController = studentNavController)
//            }
//            composable(TopBarStudentAction.Settings.route) {
//                SettingsScreen(navController = studentNavController, onLogout = onLogout)
//            }
        }
    }
}


