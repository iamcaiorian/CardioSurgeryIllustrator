package com.example.cardiosurgeryillustrator.ui.screens.student.settings_student

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


import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.navigation.LoginFlow
import com.example.cardiosurgeryillustrator.navigation.PasswordRecoveryAction
import com.example.cardiosurgeryillustrator.navigation.SettingsAction
import com.example.cardiosurgeryillustrator.navigation.WelcomeFlow

import com.example.cardiosurgeryillustrator.ui.components.student.settings_student.SettingsOption
import com.example.cardiosurgeryillustrator.ui.components.student.settings_student.SettingsOptionSwitch
import com.example.cardiosurgeryillustrator.ui.components.student.settings_student.TopBarSettings
import com.example.cardiosurgeryillustrator.view_models.student.settings.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun SettingsStudentScreen(
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
            SettingsOptionSwitch(
                title = "Modo escuro",
                isChecked = isDarkThemeEnabled,
                onCheckedChange = { viewModel.toggleTheme(navController.context) }
            )


            SettingsOption(title = "Tela de perfil", onClickOption = {
                navController.navigate(
                    SettingsAction.Profile.route
                )
            })

            SettingsOption(title = "Alterar senha", onClickOption = {
                navController.navigate(
                    PasswordRecoveryAction.GenerateCode.route
                )
            })

            SettingsOption(title = "Realizar logout", onClickOption = {

                viewModel.logout(navController.context)
                navController.navigate(
                    LoginFlow.Login.route
                )
            })

        }
    }
}

@Preview
@Composable
@ExperimentalMaterial3Api
private fun SettingsStudentScreenPreview() {
    SettingsStudentScreen(
        onNavigateBack = {},
        viewModel = SettingsViewModel(LocalContext.current),
        navController = rememberNavController()
    )
}
