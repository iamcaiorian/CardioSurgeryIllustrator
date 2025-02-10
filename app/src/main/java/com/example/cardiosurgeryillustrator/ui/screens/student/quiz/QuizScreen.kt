package com.example.cardiosurgeryillustrator.ui.screens.student.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz
import com.example.cardiosurgeryillustrator.ui.components.buttons.QuestionsButton
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.TopBarQuiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    quiz: Quiz,
    onBackClick: () -> Unit,
    onQuizFinish: () -> Unit
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    val currentQuestion = quiz.questionEntityList.getOrNull(currentQuestionIndex)
    var showFeedback by remember { mutableStateOf(false) }
    var isCorrectAnswer by remember { mutableStateOf(false) }

    if (currentQuestion == null) {
        Text(
            text = "Você completou o quiz! Parabéns!",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            style = MaterialTheme.typography.titleLarge
        )
        return
    }

    Scaffold(
        topBar = {
            TopBarQuiz(
                title = quiz.title,
                onBackClick = onBackClick,
                onMenuOptionClick = {}
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
                text = currentQuestion.problem,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    currentQuestion.alternativeA,
                    currentQuestion.alternativeB,
                    currentQuestion.alternativeC,
                    currentQuestion.alternativeD
                ).forEach { alternative ->
                    QuestionsButton(
                        text = alternative,
                        isSelected = false,
                        onClick = {
                            isCorrectAnswer = (alternative == currentQuestion.answer)
                            showFeedback = true
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (showFeedback) {
                Text(
                    text = if (isCorrectAnswer) "Correto! Você acertou!" else "Errado! A resposta correta era: ${currentQuestion.answer}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isCorrectAnswer) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        showFeedback = false
                        if (currentQuestionIndex < quiz.questionEntityList.size - 1) {
                            currentQuestionIndex++
                        } else {
                            onQuizFinish()
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Próxima Pergunta")
                }
            }
        }
    }
}
