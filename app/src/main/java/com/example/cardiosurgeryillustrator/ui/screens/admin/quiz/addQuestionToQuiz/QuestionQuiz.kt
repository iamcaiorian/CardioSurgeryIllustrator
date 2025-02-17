package com.example.cardiosurgeryillustrator.ui.screens.admin.quiz.addQuestionToQuiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.models.student.quiz.question.Question
import com.example.cardiosurgeryillustrator.models.student.quiz.question.QuestionResponse
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.Quiz
import com.example.cardiosurgeryillustrator.repository.admin.question.QuestionRepository
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules.QuizViewModel
import com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules.QuizViewModelFactory

@Composable
fun QuestionQuiz(
    modifier: Modifier = Modifier,
    quizId: String,
    onQuestionAdded: (QuestionResponse) -> Unit  // Callback para quando a questão for adicionada
) {

    val quizRepository = QuizRepository()
    val questionRepository = QuestionRepository()
    val moduleRepository = ModuleRepository()
    val factory = QuizViewModelFactory(quizRepository, questionRepository, moduleRepository)
    val viewModel: QuizViewModel = viewModel(factory = factory)

    // Estados para os campos da questão
    var problem by remember { mutableStateOf("") }
    var alternativeA by remember { mutableStateOf("") }
    var alternativeB by remember { mutableStateOf("") }
    var alternativeC by remember { mutableStateOf("") }
    var alternativeD by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        // Carregar dados do quiz se necessário
        viewModel.loadQuizzes()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Adicionar Nova Questão ao Quiz", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

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
                            onQuestionAdded(it)  // Passar o quiz atualizado ou a questão adicionada
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
            Text("Adicionar Questão")
        }
    }
}
