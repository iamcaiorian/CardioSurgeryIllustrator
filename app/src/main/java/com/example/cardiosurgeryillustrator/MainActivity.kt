package com.example.cardiosurgeryillustrator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.cardiosurgeryillustrator.ui.screens.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LoginScreen(
                    onLoginClick = { email, password ->
                        // Aqui você pode adicionar lógica ao clicar no botão de login
                    },
                    onForgotPasswordClick = {
                        // Aqui você pode adicionar lógica ao clicar em "Esqueci minha senha"
                    }
                )
            }
        }
    }
}
