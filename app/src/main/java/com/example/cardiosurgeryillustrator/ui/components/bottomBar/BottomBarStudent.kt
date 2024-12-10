package com.example.cardiosurgeryillustrator.ui.components.bottomBar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.navigation.BottomBarStudentAction

@Composable
fun BottomBarStudent(navController: NavController) {
    val actions = listOf(
        BottomBarStudentAction.Home,
        BottomBarStudentAction.Modules,
        BottomBarStudentAction.Favorites
    )

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    NavigationBar {
        actions.forEach { action ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == action.route } == true,
                onClick = {
                    navController.navigate(action.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = action.icon,
                label = {
                    Text(
                        text = action.description,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Preview
@Composable
private fun BottomBarStudentPreview() {
    BottomBarStudent(navController = rememberNavController())
}