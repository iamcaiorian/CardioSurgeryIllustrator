package com.example.cardiosurgeryillustrator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import com.example.cardiosurgeryillustrator.navigation.AppScreen
import com.example.cardiosurgeryillustrator.navigation.BottomBarStudentAction
import com.example.cardiosurgeryillustrator.navigation.NavGraph

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

                    val navigateTo = intent.getStringExtra("navigate_to")
                    val habitTitle = intent.getStringExtra("habit_title")
                    val habitDescription = intent.getStringExtra("habit_message")

                    val startDestination = when (navigateTo) {
                        "habit_detail" -> "habit_detail/${habitTitle ?: "Detalhes"}/${habitDescription ?: "Aqui você sempre manterá bons hábitos!"}"
                        else -> if (isAuthenticated) AppScreen.StudentFlow.route else AppScreen.WelcomeFlow.route
                    }

                    NavGraph(
                        isAuthenticated = isAuthenticated,
                        onLogin = { isAuthenticated = true },
                        onLogout = { isAuthenticated = false },
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}
