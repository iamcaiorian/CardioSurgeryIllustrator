package com.example.cardiosurgeryillustrator.ui.components.patient

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.navigation.BottomBarPacientAction

@Composable
fun BottomBarPacient(navController: NavController) {
    val actions = listOf(
        BottomBarPacientAction.HomePacient,
        BottomBarPacientAction.Community,
        BottomBarPacientAction.Assistant,
        BottomBarPacientAction.More
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
private fun BottomBarPacientPreview() {
    BottomBarPacient(navController = rememberNavController())
}