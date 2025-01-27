package com.example.cardiosurgeryillustrator.ui.screens.admin.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.input.StandardTextField
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc500

@Composable
fun LoginAdminScreen(
    modifier: Modifier = Modifier, onLoginClick: (String, String) -> Unit,
    onForgotPasswordClick: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Cardio Surgery",
                    fontSize = 20.sp,
                    color = Blue700,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Illustrator",
                    fontSize = 20.sp,
                    color = Blue700,
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Usuário",
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
                )
                StandardTextField(
                    value = email,
                    label = "Digite seu email",
                    onValueChange = { email = it }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Senha",
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
                )
                StandardTextField(
                    value = password,
                    label = "Digite sua senha",
                    onValueChange = { password = it }
                )
            }

            Spacer(modifier = Modifier.height(26.dp))


            StandardButton(modifier = Modifier.fillMaxWidth(),
                text = "entrar",
                onClick = {
                    onLoginClick(email, password)
                },
            )

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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginAdminScreenPreview() {
    LoginAdminScreen(
        onLoginClick = { email, password ->
            println("Login clicked with email: $email and password: $password")
        },
        onForgotPasswordClick = {
            println("Forgot Password clicked")
        },
    )
}
