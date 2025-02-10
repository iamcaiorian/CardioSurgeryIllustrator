package com.example.cardiosurgeryillustrator.ui.screens.student.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.student.quiz.CreateQuizQuestionRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz
import com.example.cardiosurgeryillustrator.ui.components.buttons.QuestionsButton
import com.example.cardiosurgeryillustrator.ui.components.buttons.ConfirmationButton
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.TopBarQuiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondQuizScreen(
    quiz: Quiz,
    questionIndex: Int,
    onBackClick: () -> Unit,
    onNextQuestion: () -> Unit
) {
    val question = quiz.questionEntityList[questionIndex]

    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var showFeedback by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBarQuiz(
                title = quiz.title,
                subtitle = "Pergunta ${questionIndex + 1} de ${quiz.questionEntityList.size}",
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
            Text(
                text = question.problem,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    question.alternativeA,
                    question.alternativeB,
                    question.alternativeC,
                    question.alternativeD
                ).forEach { alternative ->
                    QuestionsButton(
                        text = alternative,
                        isSelected = alternative == selectedAnswer,
                        onClick = {
                            selectedAnswer = alternative
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (showFeedback) {
                Text(
                    text = if (isCorrect) {
                        "Correto! A resposta é ${question.answer}."
                    } else {
                        "Errado! A resposta correta é ${question.answer}."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isCorrect) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ConfirmationButton(
                text = if (showFeedback) "Próxima" else "Confirmar",
                onClick = {
                    if (showFeedback) {
                        onNextQuestion()
                        showFeedback = false
                        selectedAnswer = null
                    } else {
                        isCorrect = (selectedAnswer ?: "") == question.answer
                        showFeedback = true
                    }
                }
            )
        }
    }
}
