package com.example.cardiosurgeryillustrator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.navigation.AppNavGraph
import com.example.cardiosurgeryillustrator.ui.theme.CardioSurgeryIllustratorTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardioSurgeryIllustratorTheme {
                Surface {
                    var isAuthenticated by remember { mutableStateOf(false) }

                    AppNavGraph(
                        isAuthenticated = isAuthenticated,
                        onLogin = {
                            isAuthenticated = true
                        },
                        onLogout = {
                            isAuthenticated = false
                        }
                    )
                }
            }
        }
    }
}


