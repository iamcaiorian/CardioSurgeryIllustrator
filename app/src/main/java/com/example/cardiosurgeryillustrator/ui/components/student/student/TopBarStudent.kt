package com.example.cardiosurgeryillustrator.ui.components.student.student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.navigation.TopBarStudentAction
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc900

@ExperimentalMaterial3Api
@Composable
fun TopBarStudent(navController: NavController) {
    val actions = listOf(
        TopBarStudentAction.Profile,
        TopBarStudentAction.Search,
        TopBarStudentAction.Settings
    )

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    TopAppBar(
        title = {
            Row (
                modifier = Modifier,
                Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically

            ){
                TopBarStudentAction.Profile.icon()
                Text(
                    text = "Estudante",
                    style = Typography.headlineSmall,
                    color = Zinc900
                )
            }
        },
        actions = {
            actions.drop(1).forEach { action ->
                IconButton(
                    onClick = {
                        navController.navigate(action.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                ) {
                    action.icon()
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun TopBarStudentPreview() {
    TopBarStudent(navController = rememberNavController())
}
