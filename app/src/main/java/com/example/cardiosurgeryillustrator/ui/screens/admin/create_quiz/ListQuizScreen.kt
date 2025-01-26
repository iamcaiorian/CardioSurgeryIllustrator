package com.example.cardiosurgeryillustrator.ui.screens.admin.create_quiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz
import com.example.cardiosurgeryillustrator.ui.components.admin.CardQuiz

@Composable
fun ListQuizScreen(
    quizzes: List<Quiz>,
    onEditQuiz: (Quiz) -> Unit,
    onDeleteQuiz: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text("Lista de Quizzes", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(quizzes) { quiz ->
            CardQuiz(
                quiz = quiz,
                onEdit = onEditQuiz,
                onDelete = onDeleteQuiz
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAdminQuizListScreen() {
    val mockQuizzes = listOf(
        Quiz(
            id = "1",
            title = "Quiz 1",
            subtitle = "Anatomia do Coração",
            question = "Quantas câmaras o coração humano possui?",
            description = "Detalhes sobre a anatomia básica do coração humano.",
            options = listOf("2", "3", "4", "5"),
            correctAnswer = "4"
        ),
        Quiz(
            id = "2",
            title = "Quiz 2",
            subtitle = "Eletrocardiograma (ECG)",
            question = "O que o ECG mede?",
            description = "Explicação sobre o que o eletrocardiograma monitora.",
            options = listOf(
                "Pressão arterial",
                "Atividade elétrica do coração",
                "Quantidade de sangue bombeado",
                "Níveis de oxigênio no sangue"
            ),
            correctAnswer = "Atividade elétrica do coração"
        )
    )

    AdminQuizListScreen(
        quizzes = mockQuizzes,
        onEditQuiz = { /* Exemplo de Callback */ },
        onDeleteQuiz = { /* Exemplo de Callback */ }
    )
}
