package com.example.cardiosurgeryillustrator.ui.screens.student.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.student.auth.AuthUserRequest
import com.example.cardiosurgeryillustrator.repository.student.auth.AuthRepository
import com.example.cardiosurgeryillustrator.ui.components.input.StandardTextField
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc500
import com.example.cardiosurgeryillustrator.view_models.student.auth.AuthViewModel
import com.example.cardiosurgeryillustrator.view_models.student.auth.AuthViewModelFactory

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(
            AuthRepository(),
            LocalContext.current
        )
    ),
    onNavigateToHome: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.heart_icon),
                contentDescription = "App Logo",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Cardio Surgery",
                    style = Typography.headlineMedium,
                    color = Blue700,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Illustrator",
                    style = Typography.headlineMedium,
                    color = Blue700,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Usuário",
                style = Typography.labelLarge,
                color = Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 4.dp),
                textAlign = TextAlign.Start
            )

            StandardTextField(
                value = email,
                label = "Digite seu email",
                onValueChange = { email = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Senha",
                style = Typography.labelLarge,
                color = Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 4.dp),
                textAlign = TextAlign.Start
            )


            StandardTextField(
                value = password,
                label = "Digite sua senha",
                onValueChange = { password = it },
                isPassword = true
            )

            Spacer(modifier = Modifier.height(26.dp))

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        isLoading = true
                        val request = AuthUserRequest(email, password)

                        authViewModel.authUser(request) { success ->
                            isLoading = false
                            if (success) {
                                Toast.makeText(context, "Login bem-sucedido!", Toast.LENGTH_SHORT)
                                    .show()
                                onNavigateToHome()
                            } else {
                                errorMessage = "Falha ao autenticar. Verifique suas credenciais."
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue700,
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text("Entrar")
                }
            }

            errorMessage?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it, color = Color.Red, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                TextButton(
                    onClick = { onForgotPasswordClick() }
                ) {
                    Text(
                        text = "Esqueci minha senha",
                        color = Zinc500,
                        style = Typography.labelMedium.copy(
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Não tem uma conta?",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Cadastre-se",
                    color = Blue700,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable { onRegisterClick() }
                        .padding(horizontal = 4.dp),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        onNavigateToHome = {},
        onForgotPasswordClick = {},
        onRegisterClick = {}
    )
}
