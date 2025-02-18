package com.example.cardiosurgeryillustrator

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.navigation.NavGraph
import com.example.cardiosurgeryillustrator.ui.theme.CardioSurgeryIllustratorTheme
import com.example.cardiosurgeryillustrator.utils.getStartDestination
import com.example.cardiosurgeryillustrator.view_models.mainScreen.MainScreenViewModel

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardioSurgeryIllustratorTheme {
                Surface {
                    MainScreen(intent = intent)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(intent: Intent, viewModel: MainScreenViewModel = viewModel()) {
    val navigateTo = intent.getStringExtra("navigate_to")
    val habitTitle = intent.getStringExtra("habit_title")
    val habitDescription = intent.getStringExtra("habit_message")

    val startDestination = remember {
        getStartDestination(navigateTo, habitTitle, habitDescription, viewModel.isAuthenticated)
    }

    NavGraph(startDestination = startDestination)
}


