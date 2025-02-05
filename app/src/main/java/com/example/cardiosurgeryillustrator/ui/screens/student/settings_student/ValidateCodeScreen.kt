package com.example.cardiosurgeryillustrator.ui.screens.student.settings_student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.navigation.SettingsAction
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.buttons.TransparentButton
import com.example.cardiosurgeryillustrator.ui.components.student.settings_student.TopBarSettings
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidateCodeScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    navController: NavController,
) {
    Scaffold(topBar = {
        TopBarSettings(
            modifier,
            onNavigateBack,
            title = "Validar código"
        )
    }) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Botão para enviar código por e-mail
                TransparentButton(
                    text = "Enviar código por e-mail",
                    onClick = {},
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Campos para digitar o código
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(4) {
                        OutlinedTextField(
                            value = "",
                            onValueChange = { },
                            modifier = Modifier
                                .size(60.dp),
                            singleLine = true,
                            maxLines = 1,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Blue700,
                                focusedLabelColor = Blue700
                            ),
                            textStyle = Typography.labelMedium,
                            shape = RoundedCornerShape(8.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                StandardButton(
                    text = "Confirmar código",
                    onClick = {
                        navController.navigate(
                            SettingsAction.ChangePassword.route
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}



@Preview
@Composable
private fun ValidateCodeScreenPreview() {
    ValidateCodeScreen(onNavigateBack = {}, navController = rememberNavController())
}