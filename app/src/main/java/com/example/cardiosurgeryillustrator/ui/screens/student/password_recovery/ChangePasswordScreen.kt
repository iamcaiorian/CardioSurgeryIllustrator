package com.example.cardiosurgeryillustrator.ui.screens.student.password_recovery

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.student.password_recovery.PasswordRecoveryRequest
import com.example.cardiosurgeryillustrator.repository.student.password_recovery.PasswordRecoveryRepository
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.patient.form.TextInputField
import com.example.cardiosurgeryillustrator.ui.components.student.settings_student.TopBarSettings
import com.example.cardiosurgeryillustrator.ui.components.topBar.StandardTopBar
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.view_models.student.password_recovery.PasswordRecoveryViewModel
import com.example.cardiosurgeryillustrator.view_models.student.password_recovery.PasswordRecoveryViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    modifier: Modifier = Modifier,
    email: String,
    code: String,
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val context = LocalContext.current

    val passwordRecoveryRepository = PasswordRecoveryRepository()
    val viewModel: PasswordRecoveryViewModel = viewModel(
        factory = PasswordRecoveryViewModelFactory(passwordRecoveryRepository)
    )

    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var responseMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(topBar = {
        StandardTopBar(
            modifier,
            onNavigateBack,
            title = "Alterar senha"
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
                    .padding(horizontal = 16.dp),
            ) {
                TextInputField(
                    label = "Nova Senha",
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    modifier = Modifier.padding(vertical = 16.dp),
                    isPassword = true
                )

                TextInputField(
                    label = "Confirmar Senha",
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    modifier = Modifier.padding(vertical = 16.dp),
                    isPassword = true
                )

                if (responseMessage != null) {
                    Text(
                        text = responseMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        style = Typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                StandardButton(
                    text = "Alterar senha",
                    iconRes = R.drawable.ic_selected,
                    enabled = newPassword.isNotEmpty() && confirmPassword.isNotEmpty(),
                    onClick = {
                        if (newPassword == confirmPassword) {
                            isLoading = true
                            val request = PasswordRecoveryRequest(
                                email = email,
                                code = code,
                                password = newPassword
                            )
                            viewModel.changePassword(request) { response ->
                                isLoading = false
                                Log.d("ChangePassword", "Response: ${response.code()} - ${response.message()}")
                                if (response.isSuccessful) {
                                    Toast.makeText(
                                        context,
                                        "Senha alterada com sucesso!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    onNavigateToLogin()
                                } else {
                                    responseMessage =
                                        response.errorBody()?.string() ?: "Erro ao alterar senha."
                                }
                            }
                        } else {
                            responseMessage = "As senhas não coincidem!"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
    }
}

@Preview
@Composable
private fun ChangePasswordPreview() {
    ChangePasswordScreen(
        onNavigateBack = {},
        email = "email@example.com",
        code = "123456",
        onNavigateToLogin = {})
}
