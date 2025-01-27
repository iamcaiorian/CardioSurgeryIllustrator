package com.example.cardiosurgeryillustrator.ui.screens.student.quiz

import ErrorButton
import com.example.cardiosurgeryillustrator.ui.components.button.SuccessButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz
import com.example.cardiosurgeryillustrator.models.mock.mockQuizzes
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.TopBarQuiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    quiz: Quiz,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMenuOptionClick: (String) -> Unit,
    onAnswerClick: (Boolean) -> Unit,
    onNavigateToSecondQuiz: (String?) -> Unit
) {
    Scaffold(
        topBar = {
            TopBarQuiz(
                title = quiz.title ?: "Quiz sem título",
                onBackClick = onBackClick,
                onMenuOptionClick = onMenuOptionClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.coracao_icon),
                contentDescription = "Imagem do coração",
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(4.dp)
                    .width(120.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Este é o conteúdo do quiz",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = quiz.description ?: "Descrição não fornecida",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ErrorButton(
                    text = "Errado",
                    onClick = {
                        onAnswerClick(false)
                        onNavigateToSecondQuiz(quiz.id)
                    }
                )
                SuccessButton(
                    text = "Certo",
                    onClick = {
                        onAnswerClick(true)
                        onNavigateToSecondQuiz(quiz.id)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizScreenMockPreview() {
    QuizScreen(
        quiz = mockQuizzes[0],
        onBackClick = { println("Voltar clicado") },
        onMenuOptionClick = { option -> println("Menu clicado: $option") },
        onAnswerClick = { isCorrect -> println("Resposta clicada: $isCorrect") },
        onNavigateToSecondQuiz = { println("Navegar para SecondQuizScreen com id $it") }
    )
}
