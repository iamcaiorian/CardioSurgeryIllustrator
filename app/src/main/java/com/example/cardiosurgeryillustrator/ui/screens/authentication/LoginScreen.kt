package com.example.cardiosurgeryillustrator.ui.screens.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.input.StandardTextField
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc500

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold {innerPadding ->
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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Entrar",
                    color = if (selectedTab == 0) Color.DarkGray else Color.Gray,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable { selectedTab = 0 },
                    fontSize = 16.sp
                )

                Text(
                    text = "|",
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Cadastre-se",
                    color = if (selectedTab == 1) Color.DarkGray else Color.Gray,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable {
                            selectedTab = 1
                            onRegisterClick() // Navegar para a tela de cadastro
                        },
                    fontSize = 16.sp
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
                    style = Typography.labelMedium,
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
                    style = Typography.labelMedium,
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

            Button(
                onClick = onNavigateToHome,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue700,
                    contentColor = Color.White
                ),
                shape = MaterialTheme.shapes.small
            ) {
                Text("Entrar")
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
