package com.example.cardiosurgeryillustrator.ui.screens.admin.create_quiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz
import com.example.cardiosurgeryillustrator.ui.components.admin.CardQuiz

@Composable
fun ListQuizScreen(
    quizzes: List<Quiz>,
    onEditQuiz: (Quiz) -> Unit,
    onDeleteQuiz: (String) -> Unit,
    onCreateQuiz: () -> Unit,
    modifier: Modifier = Modifier
) {
    println("Quizzes recebidos na tela: $quizzes")
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Lista de Quizzes", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCreateQuiz,
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF005BAC),
                contentColor = Color.White
            )
        ) {
            Text("Criar Quiz")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            if (quizzes.isEmpty()) {
                item {
                    Text(
                        text = "Nenhum quiz disponível. Adicione um novo quiz.",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            } else {
                items(quizzes) { quiz ->
                    CardQuiz(
                        quiz = quiz,
                        onEdit = onEditQuiz,
                        onDelete = onDeleteQuiz
                    )
                }
            }
        }
    }
}
