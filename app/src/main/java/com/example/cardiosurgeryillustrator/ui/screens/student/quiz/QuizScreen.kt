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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.Quiz
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import com.example.cardiosurgeryillustrator.ui.components.button.QuestionsButton
import com.example.cardiosurgeryillustrator.ui.components.buttons.ConfirmationButton
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.TopBarQuiz
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.view_models.student.quiz.QuizViewModel
import com.example.cardiosurgeryillustrator.view_models.student.quiz.QuizViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    quizId: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val quizRepository = QuizRepository()
    val viewModel: QuizViewModel = viewModel(
        factory = QuizViewModelFactory(quizRepository)
    )

    var isLoading by remember { mutableStateOf(true) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var resultMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(quizId) {
        viewModel.getQuizById(quizId)
        isLoading = false
    }

    val quiz = viewModel.quiz

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
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            quiz?.let { currentQuiz ->
                val question = currentQuiz.questionEntityList?.firstOrNull()

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
                        text = question?.problem ?: "Pergunta não disponível.",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        question?.let {
                            listOf(
                                it.alternativeA to "A",
                                it.alternativeB to "B",
                                it.alternativeC to "C",
                                it.alternativeD to "D"
                            ).forEach { (alternative, label) ->
                                val isSelected = selectedAnswer == label  // Armazenando apenas a letra
                                QuestionsButton(
                                    text = "$label. $alternative",
                                    isSelected = isSelected,
                                    onClick = {
                                        selectedAnswer = label  // A resposta será apenas a letra da alternativa
                                    }
                                )
                            }
                        } ?: Text(
                            text = "Nenhuma opção disponível.",
                            style = Typography.bodyMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    ConfirmationButton(
                        text = "Confirmar",
                        onClick = {
                            resultMessage = when {
                                selectedAnswer == null -> {
                                    "Nenhuma resposta selecionada."
                                }
                                selectedAnswer.equals(question?.answer, ignoreCase = true) -> {
                                    "Parabéns! Você acertou a resposta!"
                                }
                                else -> {
                                    "Resposta errada! A alternativa correta é: ${question?.answer}"
                                }
                            }
                        }
                    )

                    resultMessage?.let {
                        Text(
                            text = it,
                            color = if (it.startsWith("Parabéns")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                        )
                    }
                }
            } ?: run {
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

