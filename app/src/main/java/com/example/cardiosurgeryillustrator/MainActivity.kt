package com.example.cardiosurgeryillustrator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val isAuthenticated = false // Mock de autenticação (substitua pela lógica real)
                    val navController = rememberNavController()

                    AppNavGraph(
                        isAuthenticated = isAuthenticated,
                        onLogin = { /* Lógica para login bem-sucedido */ },
                        onLogout = { /* Lógica para logout */ }
                    )
                }
            }
        }
    }
}