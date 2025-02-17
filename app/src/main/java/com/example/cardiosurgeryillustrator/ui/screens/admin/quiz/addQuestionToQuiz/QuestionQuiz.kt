package com.example.cardiosurgeryillustrator.ui.screens.admin.quiz.addQuestionToQuiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.models.student.quiz.question.QuestionResponse
import com.example.cardiosurgeryillustrator.repository.admin.question.QuestionRepository
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules.QuizViewModel
import com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules.QuizViewModelFactory

@Composable
fun QuestionQuiz(
    modifier: Modifier = Modifier,
    quizId: String,
    onQuestionAdded: (QuestionResponse) -> Unit
) {
    val quizRepository = QuizRepository()
    val questionRepository = QuestionRepository()
    val moduleRepository = ModuleRepository()
    val factory = QuizViewModelFactory(quizRepository, questionRepository, moduleRepository)
    val viewModel: QuizViewModel = viewModel(factory = factory)

    var problem by remember { mutableStateOf("") }
    var alternativeA by remember { mutableStateOf("") }
    var alternativeB by remember { mutableStateOf("") }
    var alternativeC by remember { mutableStateOf("") }
    var alternativeD by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadQuizzes()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Adicionar Nova Questão ao Quiz",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = problem,
            onValueChange = { problem = it },
            label = { Text("Pergunta") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = alternativeA,
            onValueChange = { alternativeA = it },
            label = { Text("Alternativa A") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = alternativeB,
            onValueChange = { alternativeB = it },
            label = { Text("Alternativa B") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = alternativeC,
            onValueChange = { alternativeC = it },
            label = { Text("Alternativa C") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = alternativeD,
            onValueChange = { alternativeD = it },
            label = { Text("Alternativa D") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = answer,
            onValueChange = { answer = it },
            label = { Text("Resposta Correta") },
            modifier = Modifier.fillMaxWidth()
        )

        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (problem.isNotBlank() && alternativeA.isNotBlank() && alternativeB.isNotBlank() &&
                    alternativeC.isNotBlank() && alternativeD.isNotBlank() && answer.isNotBlank()
                ) {
                    viewModel.addQuestionToQuiz(
                        problem = problem,
                        alternativeA = alternativeA,
                        alternativeB = alternativeB,
                        alternativeC = alternativeC,
                        alternativeD = alternativeD,
                        answer = answer,
                        quizId = quizId,
                        onSuccess = {
                            onQuestionAdded(it)
                            errorMessage = null
                        },
                        onError = { errorMessage = it }
                    )
                } else {
                    errorMessage = "Por favor, preencha todos os campos!"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF005BAC),
                contentColor = Color.White
            )
        ) {
            Text("Adicionar Questão")
        }
    }
}
