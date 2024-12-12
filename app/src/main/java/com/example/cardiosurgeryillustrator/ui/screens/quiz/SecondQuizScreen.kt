package com.example.cardiosurgeryillustrator.ui.screens.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.button.ConfirmationButton
import com.example.cardiosurgeryillustrator.ui.components.button.QuestionsButton
import com.example.cardiosurgeryillustrator.ui.components.topBar.TopBarQuiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondQuizScreen(
    title: String = "Quiz 1",
    subtitle: String = "Assunto 2",
    question: String = "Qual a principal função da artéria coronária direita?",
    onBackClick: () -> Unit,
    onMenuOptionClick: (String) -> Unit,
    onAnswerClick: (Boolean) -> Unit
) {
    // Estado para controlar qual botão está selecionado
    var selectedOption by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopBarQuiz(
                title = title,
                subtitle = subtitle,
                onBackClick = onBackClick,
                onMenuOptionClick = onMenuOptionClick
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
            // Imagem no topo
            Image(
                painter = painterResource(id = R.drawable.coracao_icon),
                contentDescription = "Imagem do coração",
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            // Título da pergunta
            Text(
                text = question,
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start)
            )

            // Botões de opções
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val options = listOf(
                    "Transportar oxigênio para o coração",
                    "Levar sangue arterial para o miocárdio",
                    "Retirar dióxido de carbono do coração",
                    "Controlar os batimentos cardíacos"
                )

                options.forEach { option ->
                    QuestionsButton(
                        text = option,
                        isSelected = selectedOption == option,
                        onClick = { selectedOption = option }
                    )
                }
            }

            // Botão de confirmação
            Spacer(modifier = Modifier.height(32.dp))
            ConfirmationButton(
                text = "Confirmar",
                onClick = {
                    onAnswerClick(selectedOption == "Levar sangue arterial para o miocárdio")
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SecondQuizScreenPreview() {
    SecondQuizScreen(
        title = "Quiz 1",
        subtitle = "Assunto 2",
        question = "Qual a principal função da artéria coronária direita?",
        onBackClick = { println("Voltar clicado") },
        onMenuOptionClick = { option -> println("Menu clicado: $option") },
        onAnswerClick = { isCorrect -> println("Resposta clicada: $isCorrect") }
    )
}
