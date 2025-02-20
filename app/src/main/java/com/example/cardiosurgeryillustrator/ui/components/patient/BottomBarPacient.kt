package com.example.cardiosurgeryillustrator.ui.components.patient

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cardiosurgeryillustrator.navigation.BottomBarPacientAction
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils

@Composable
fun BottomBarPacient(navController: NavController) {
    val context = LocalContext.current
    val diseaseIndexFlow = DataStoreUtils.readUserDiseaseIndex(context)
    val diseaseIndex = diseaseIndexFlow.collectAsStateWithLifecycle(initialValue = 0)

    val actions = listOf(
        "${BottomBarPacientAction.HomePacient.route}/${diseaseIndex.value}",
        BottomBarPacientAction.Community.route,
        BottomBarPacientAction.Assistant.route,
        BottomBarPacientAction.More.route
    )

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    NavigationBar {
        actions.forEach { action ->
            val isHomePacient = action.startsWith(BottomBarPacientAction.HomePacient.route)

            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == action } == true,
                onClick = {
                    val route = if (isHomePacient) {
                        "${BottomBarPacientAction.HomePacient.route}/${diseaseIndex.value}"
                    } else {
                        action
                    }

                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    val actionObj = listOf(
                        BottomBarPacientAction.HomePacient,
                        BottomBarPacientAction.Community,
                        BottomBarPacientAction.Assistant,
                        BottomBarPacientAction.More
                    ).find { it.route == action.split("/").first() }

                    actionObj?.icon?.invoke()
                },
                label = {
                    val actionObj = listOf(
                        BottomBarPacientAction.HomePacient,
                        BottomBarPacientAction.Community,
                        BottomBarPacientAction.Assistant,
                        BottomBarPacientAction.More
                    ).find { it.route == action.split("/").first() }

                    Text(
                        text = actionObj?.description ?: "",
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Blue700,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }

}