package com.example.cardiosurgeryillustrator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cardiosurgeryillustrator.ui.screens.CardioForm
import com.example.cardiosurgeryillustrator.ui.theme.CardioSurgeryIllustratorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardioSurgeryIllustratorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CardioForm()
                }
            }
        }
    }
}
