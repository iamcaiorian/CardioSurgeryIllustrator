package com.example.cardiosurgeryillustrator.ui.screens.admin.create_quiz

import com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules.CreateQuizViewModel
import com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules.CreateQuizViewModelFactory
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.student.quiz.CreateQuizQuestionRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.CreateQuizRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz
import com.example.cardiosurgeryillustrator.models.student.quiz.QuizQuestion
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import kotlinx.coroutines.launch

@Composable
fun AdminAddQuizScreen(
    onQuizAdded: (CreateQuizRequest) -> Unit,
    modifier: Modifier = Modifier
) {
    val quizRepository = QuizRepository()
    val factory = CreateQuizViewModelFactory(quizRepository)
    val viewModel: CreateQuizViewModel = viewModel(factory = factory)

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var problem by remember { mutableStateOf("") }
    var alternativeA by remember { mutableStateOf("") }
    var alternativeB by remember { mutableStateOf("") }
    var alternativeC by remember { mutableStateOf("") }
    var alternativeD by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
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
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = problem,
            onValueChange = { problem = it },
            label = { Text("Pergunta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = alternativeA,
            onValueChange = { alternativeA = it },
            label = { Text("Alternativa A") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = alternativeB,
            onValueChange = { alternativeB = it },
            label = { Text("Alternativa B") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = alternativeC,
            onValueChange = { alternativeC = it },
            label = { Text("Alternativa C") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = alternativeD,
            onValueChange = { alternativeD = it },
            label = { Text("Alternativa D") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = answer,
            onValueChange = { answer = it },
            label = { Text("Resposta Correta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {
                if (title.isNotBlank() && description.isNotBlank() && problem.isNotBlank() &&
                    alternativeA.isNotBlank() && alternativeB.isNotBlank() &&
                    alternativeC.isNotBlank() && alternativeD.isNotBlank() && answer.isNotBlank()
                ) {
                    val quiz = CreateQuizRequest(
                        title = title,
                        description = description,
                        questionEntityList = listOf(
                            CreateQuizQuestionRequest(
                                problem = problem,
                                alternativeA = alternativeA,
                                alternativeB = alternativeB,
                                alternativeC = alternativeC,
                                alternativeD = alternativeD,
                                answer = answer
                            )
                        )
                    )
                    viewModel.createQuiz(
                        quiz = quiz,
                        onSuccess = {
                            onQuizAdded(it)
                            errorMessage = null
                        },
                        onError = { errorMessage = it }
                    )
                } else {
                    errorMessage = "Por favor, preencha todos os campos!"
                }
            },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF005BAC),
                contentColor = Color.White
            )
        ) {
            Text("Salvar Quiz")
        }
    }
}



@Composable
fun AdminQuizListScreen(
    quizzes: List<Quiz>,
    onEditQuiz: (Quiz) -> Unit,
    onDeleteQuiz: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
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
                    Text(quiz.description, style = MaterialTheme.typography.bodyMedium)

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
                            onClick = { onDeleteQuiz(quiz.id.orEmpty()) },
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