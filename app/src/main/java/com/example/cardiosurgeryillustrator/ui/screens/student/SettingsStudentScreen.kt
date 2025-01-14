package com.example.cardiosurgeryillustrator.ui.screens.student

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.cardiosurgeryillustrator.ui.components.settings.SettingsOption
import com.example.cardiosurgeryillustrator.ui.components.settings.SettingsOptionSwitch
import com.example.cardiosurgeryillustrator.ui.components.settings.TopBarSettings
import com.example.cardiosurgeryillustrator.ui.view_models.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun SettingsStudentScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = SettingsViewModel(LocalContext.current)
) {
    val isDarkThemeEnabled by viewModel.isDarkTheme.collectAsState()

    Scaffold(topBar = { TopBarSettings(modifier, onNavigateBack) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            SettingsOption(
                title = "Notificações",
                onClickOption = {
                    navController.navigate("notifications")
                }
            )

            SettingsOptionSwitch(
                title = "Modo escuro",
                isChecked = isDarkThemeEnabled,
                onCheckedChange = { viewModel.toggleTheme(navController.context) }
            )
        }
    }
}
