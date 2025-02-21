package com.example.cardiosurgeryillustrator.ui.screens.student.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import com.example.cardiosurgeryillustrator.ui.components.buttons.QuestionsButton
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.TopBarQuiz
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.view_models.student.quiz.QuizViewModel
import com.example.cardiosurgeryillustrator.view_models.student.quiz.QuizViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    quizId: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onFinishQuiz: (Int, Int) -> Unit
) {
    val quizRepository = QuizRepository(LocalContext.current)
    val viewModel: QuizViewModel = viewModel(
        factory = QuizViewModelFactory(quizRepository)
    )

    var isLoading by remember { mutableStateOf(true) }
    var currentIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var correctAnswers by remember { mutableStateOf(0) }
    val quiz by viewModel.quiz.collectAsState()

    LaunchedEffect(quizId) {
        isLoading = true
        viewModel.getQuizById(quizId)
    }

    val questions = quiz?.questionEntityList ?: emptyList()
    val totalQuestions = questions.size

    if (quiz != null) {
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopBarQuiz(
                title = quiz?.title ?: "Carregando...",
                subtitle = "Detalhes do Quiz",
                onBackClick = onBackClick,
                onMenuOptionClick = {}
            )
        }
    ) { innerPadding ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Blue700)
                }
            }
            totalQuestions > 0 -> {
                val currentQuestion = questions[currentIndex]

                Column(
                    modifier = modifier
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
                            .size(150.dp)
                            .padding(8.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = "Pergunta ${currentIndex + 1} de $totalQuestions",
                        style = Typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = currentQuestion.problem,
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
                            currentQuestion.alternativeA to "A",
                            currentQuestion.alternativeB to "B",
                            currentQuestion.alternativeC to "C",
                            currentQuestion.alternativeD to "D"
                        ).forEach { (alternative, label) ->
                            QuestionsButton(
                                text = "$label. $alternative",
                                isSelected = selectedAnswer == label,
                                onClick = { selectedAnswer = label }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    StandardButton(
                        text = if (currentIndex < totalQuestions - 1) "Próxima" else "Finalizar",
                        onClick = {
                            if (selectedAnswer.equals(currentQuestion.answer, ignoreCase = true)) {
                                correctAnswers++
                            }

                            if (currentIndex < totalQuestions - 1) {
                                selectedAnswer = null
                                currentIndex++
                            } else {
                                onFinishQuiz(correctAnswers, totalQuestions)
                            }
                        },
                        iconRes = R.drawable.ic_selected
                    )
                }
            }
            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Erro ao carregar quiz.", style = Typography.titleLarge)
                }
            }
        }
    }
}
