package com.example.cardiosurgeryillustrator.ui.screens.admin.create_quiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz

@Composable
fun AdminAddQuizScreen(onQuizAdded: (Quiz) -> Unit) {
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var question by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var options by remember { mutableStateOf(listOf("", "", "", "")) }
    var correctAnswer by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Adicionar Novo Quiz", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = subtitle,
            onValueChange = { subtitle = it },
            label = { Text("Subtítulo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = question,
            onValueChange = { question = it },
            label = { Text("Pergunta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Opções (4 alternativas):", style = MaterialTheme.typography.bodyLarge)
        options.forEachIndexed { index, option ->
            OutlinedTextField(
                value = option,
                onValueChange = { newValue ->
                    options = options.toMutableList().apply { set(index, newValue) }
                },
                label = { Text("Opção ${index + 1}") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = correctAnswer,
            onValueChange = { correctAnswer = it },
            label = { Text("Resposta Correta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val newQuiz = Quiz(
                    id = System.currentTimeMillis().toString(),
                    title = title,
                    subtitle = subtitle,
                    question = question,
                    description = description,
                    options = options,
                    correctAnswer = correctAnswer
                )
                onQuizAdded(newQuiz)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Salvar Quiz")
        }
    }
}

@Composable
fun AdminQuizListScreen(
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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(quiz.title, style = MaterialTheme.typography.titleMedium)
                    Text(quiz.subtitle, style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = { onEditQuiz(quiz) }) {
                            Text("Editar")
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = { onDeleteQuiz(quiz.id) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AdminScreen() {
    val quizzes = remember { mutableStateListOf<Quiz>() }

    var currentScreen by remember { mutableStateOf("list") }
    var quizToEdit by remember { mutableStateOf<Quiz?>(null) }

    when (currentScreen) {
        "list" -> AdminQuizListScreen(
            quizzes = quizzes,
            onEditQuiz = {
                quizToEdit = it
                currentScreen = "edit"
            },
            onDeleteQuiz = { quizId ->
                quizzes.removeAll { it.id == quizId }
            }
        )
        "edit" -> AdminAddQuizScreen(
            onQuizAdded = { updatedQuiz ->
                quizzes.replaceAll { if (it.id == updatedQuiz.id) updatedQuiz else it }
                currentScreen = "list"
            }
        )
        else -> AdminAddQuizScreen(
            onQuizAdded = { newQuiz ->
                quizzes.add(newQuiz)
                currentScreen = "list"
            }
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAdminAddQuizScreen() {
    AdminAddQuizScreen(onQuizAdded = { /* Exemplo de Callback */ })
}
