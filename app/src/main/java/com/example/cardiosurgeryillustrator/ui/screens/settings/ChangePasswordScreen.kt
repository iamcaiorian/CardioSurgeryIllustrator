package com.example.cardiosurgeryillustrator.ui.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.patient.form.TextInputField
import com.example.cardiosurgeryillustrator.ui.components.settings.TopBarSettings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(modifier: Modifier = Modifier, onNavigateBack: () -> Unit) {
    Scaffold(topBar = {
        TopBarSettings(
            modifier,
            onNavigateBack,
            title = "Alterar senha"
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),

            ) {


                TextInputField(
                    label = "Nova Senha",
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.padding(vertical = 16.dp),
                    isPassword = true
                )

                StandardButton(
                    text = "Alterar senha",
                    iconRes = R.drawable.ic_selected,
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
private fun ChangePasswordPreview() {
    ChangePasswordScreen(onNavigateBack = {})
}