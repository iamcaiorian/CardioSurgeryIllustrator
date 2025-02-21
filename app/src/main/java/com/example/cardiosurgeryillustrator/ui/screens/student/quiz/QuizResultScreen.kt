package com.example.cardiosurgeryillustrator.ui.screens.student.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizResultScreen(
    correctAnswers: Int,
    totalQuestions: Int,
    onBackToHome: () -> Unit
) {
    val percentage = (correctAnswers.toFloat() / totalQuestions) * 100
    val message = when {
        percentage > 80 -> "Parabéns! Você mandou muito bem! 🎉"
        percentage in 50.0..80.0 -> "Bom trabalho! Mas você pode melhorar! 👍"
        else -> "Continue estudando! Você consegue! 📖"
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar( 
                title = {
                    Text(
                        "Resultado do Quiz",
                        style = Typography.headlineLarge
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Você acertou $correctAnswers de $totalQuestions questões!",
                style = Typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = message,
                style = Typography.headlineSmall,
            )

            Spacer(modifier = Modifier.height(24.dp))

            StandardButton(
                onClick = onBackToHome,
                text = "Voltar para home"
            )
        }
    }
}


@Preview
@Composable
private fun QuizResultScreenPreview() {
    QuizResultScreen(correctAnswers = 3, totalQuestions = 5, onBackToHome = {})
}
