package com.example.cardiosurgeryillustrator.ui.screens.student.password_recovery

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.models.student.password_recovery.PasswordRecoveryRequest
import com.example.cardiosurgeryillustrator.view_models.student.password_recovery.PasswordRecoveryViewModel
import com.example.cardiosurgeryillustrator.view_models.student.password_recovery.PasswordRecoveryViewModelFactory
import com.example.cardiosurgeryillustrator.repository.student.password_recovery.PasswordRecoveryRepository
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.topBar.StandardTopBar
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidateCodeScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    email: String,
    onNavigateToChangePassword: (email: String, code: String) -> Unit,
    viewModel: PasswordRecoveryViewModel = viewModel(
        factory = PasswordRecoveryViewModelFactory(PasswordRecoveryRepository())
    )
) {
    val focusRequesters = List(6) { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val code = remember { mutableStateListOf("", "", "", "", "", "") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(topBar = {
        StandardTopBar(
            modifier,
            onNavigateBack,
            title = "Validar código"
        )
    }) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(6) { index ->
                        OutlinedTextField(
                            value = code[index],
                            onValueChange = { newValue ->
                                if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                                    code[index] = newValue
                                    if (newValue.isNotEmpty() && index < 5) {
                                        focusRequesters[index + 1].requestFocus()
                                    } else if (newValue.isEmpty() && index > 0) {
                                        focusRequesters[index - 1].requestFocus()
                                    }
                                }
                            },
                            modifier = Modifier
                                .size(50.dp)
                                .focusRequester(focusRequesters[index]),
                            singleLine = true,
                            maxLines = 1,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Blue700,
                                unfocusedBorderColor = Blue700
                            ),
                            textStyle = Typography.headlineMedium,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                errorMessage?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        style = Typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                StandardButton(
                    text = "Confirmar código",
                    onClick = {
                        val fullCode = code.joinToString("")
                        if (fullCode.length == 6) {
                            isLoading = true

                            var passwordRecoveryRequest = PasswordRecoveryRequest(email, fullCode)

                            viewModel.validCode(passwordRecoveryRequest) { success ->
                                isLoading = false
                                if (success.isSuccessful) {
                                    onNavigateToChangePassword(email, fullCode)
                                } else {
                                    errorMessage = "Código inválido. Tente novamente."
                                }
                            }
                        }
                    },
                    enabled = code.all { it.isNotEmpty() } && !isLoading,
                    modifier = Modifier.fillMaxWidth()
                )

                if (isLoading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview
@Composable
private fun ValidateCodeScreenPreview() {
    ValidateCodeScreen(
        onNavigateBack = {},
        onNavigateToChangePassword = { _, _ -> },
        email = "teste@example.com"
    )
}
