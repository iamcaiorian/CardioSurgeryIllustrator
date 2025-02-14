package com.example.cardiosurgeryillustrator.ui.screens.student.password_recovery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.models.student.password_recovery.PasswordRecoveryRequest
import com.example.cardiosurgeryillustrator.repository.student.password_recovery.PasswordRecoveryRepository
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.input.StandardTextField
import com.example.cardiosurgeryillustrator.ui.components.topBar.StandardTopBar
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.view_models.student.password_recovery.PasswordRecoveryViewModel
import com.example.cardiosurgeryillustrator.view_models.student.password_recovery.PasswordRecoveryViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateCodeScreen(
    modifier: Modifier = Modifier,
    viewModel: PasswordRecoveryViewModel = viewModel(
        factory = PasswordRecoveryViewModelFactory(PasswordRecoveryRepository())
    ),
    onNavigateBack: () -> Unit,
    onNavigateToValidateCode: (email: String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        StandardTopBar(
            modifier,
            onNavigateBack,
            title = "Gerar código"
        )
    }) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StandardTextField(
                    value = email,
                    label = "Digite seu email",
                    onValueChange = { email = it }
                )

                StandardButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        isLoading = true
                        viewModel.generateToken(PasswordRecoveryRequest(email = email)) { response ->
                            isLoading = false

                            if (response.isSuccessful) {
                                message = "Código enviado com sucesso!"
                                onNavigateToValidateCode(email)
                            } else {
                                message = "Erro ao enviar código."
                            }

                        }
                    },
                    text = "Enviar código"
                )

                if (isLoading) {
                    CircularProgressIndicator(color = Blue700)
                }

                message?.let {
                    Text(text = it, color = if (it.contains("sucesso")) Color.Green else Color.Red)
                }
            }
        }
    }


}


@Preview
@Composable
private fun GenerateTokenScreenPreview() {
    GenerateCodeScreen(onNavigateToValidateCode = {}, onNavigateBack = {})
}