package com.example.cardiosurgeryillustrator.ui.screens.patient.settings

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
import com.example.cardiosurgeryillustrator.ui.components.student.settings_student.SettingsOption
import com.example.cardiosurgeryillustrator.ui.components.student.settings_student.SettingsOptionSwitch
import com.example.cardiosurgeryillustrator.ui.components.student.settings_student.TopBarSettings
import com.example.cardiosurgeryillustrator.view_models.student.settings.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientSettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = SettingsViewModel(LocalContext.current)
) {
    val isDarkThemeEnabled by viewModel.isDarkTheme.collectAsState()

    Scaffold(topBar = {
        TopBarSettings(
            modifier,
            onNavigateBack,
            title = "Configurações"
        )
    }) { innerPadding ->
        Column(
            modifier = modifier
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