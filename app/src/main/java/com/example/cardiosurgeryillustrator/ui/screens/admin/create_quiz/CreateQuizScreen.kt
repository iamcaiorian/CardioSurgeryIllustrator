package com.example.cardiosurgeryillustrator.ui.screens.admin.create_quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.models.student.module.ModuleResponse
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.Quiz
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import com.example.cardiosurgeryillustrator.repository.admin.question.QuestionRepository
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import com.example.cardiosurgeryillustrator.ui.components.admin.create_module.SelectModule
import com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules.QuizViewModel
import com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules.QuizViewModelFactory

@Composable
fun AdminAddQuizScreen(
    onQuizAdded: (Quiz) -> Unit,
    modifier: Modifier = Modifier
) {
    val quizRepository = QuizRepository(LocalContext.current)
    val questionRepository = QuestionRepository()
    val moduleRepository = ModuleRepository()
    val factory = QuizViewModelFactory(quizRepository, questionRepository, moduleRepository)
    val viewModel: QuizViewModel = viewModel(factory = factory)

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var problem by remember { mutableStateOf("") }
    var alternativeA by remember { mutableStateOf("") }
    var alternativeB by remember { mutableStateOf("") }
    var alternativeC by remember { mutableStateOf("") }
    var alternativeD by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Estado para módulos
    val modules by viewModel.modules.collectAsState()
    var selectedModule by remember { mutableStateOf<ModuleResponse?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadModules()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Adicionar Novo Quiz", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown de módulos
        SelectModule(
            modules = modules,
            selectedModule = selectedModule,
            onModuleSelected = { selectedModule = it }
        )

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
                    alternativeC.isNotBlank() && alternativeD.isNotBlank() &&
                    answer.isNotBlank() && selectedModule != null
                ) {
                    viewModel.createQuiz(
                        title = title,
                        description = description,
                        problem = problem,
                        alternativeA = alternativeA,
                        alternativeB = alternativeB,
                        alternativeC = alternativeC,
                        alternativeD = alternativeD,
                        answer = answer,
                        moduleId = selectedModule!!.id,
                        onSuccess = {
                            onQuizAdded(it)
                            errorMessage = null
                        },
                        onError = { errorMessage = it }
                    )
                } else {
                    errorMessage = "Por favor, preencha todos os campos e selecione um módulo!"
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
