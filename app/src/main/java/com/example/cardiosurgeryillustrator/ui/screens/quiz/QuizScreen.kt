package com.example.cardiosurgeryillustrator.ui.screens.quiz

import ErrorButton
import com.example.cardiosurgeryillustrator.ui.components.button.SuccessButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.topBar.TopBarQuiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    title: String = "Quiz 1",
    subtitle: String = "Assunto 1",
    question: String = "Pergunta 9",
    description: String = "Dado os tipos de coração existentes, apenas um é adulto e todos possuem obstruções cardíacas.",
    onBackClick: () -> Unit,
    onMenuOptionClick: (String) -> Unit,
    onAnswerClick: (Boolean) -> Unit
) {
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
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.coracao_icon),
                    contentDescription = "Imagem 1",
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .padding(4.dp)
                        .width(120.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = question,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start)
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ErrorButton(
                    text = "Errado",
                    onClick = { onAnswerClick(false) }
                )
                SuccessButton(
                    text = "Certo",
                    onClick = { onAnswerClick(true) }
                )
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizScreenPreview() {
    QuizScreen(
        title = "Quiz 1",
        subtitle = "Assunto 1",
        question = "Pergunta 1",
        description = "Dado os tipos de corações existentes, " +
                "apenas um é adulto e todos possuem obstruções cardíacas.",
        onBackClick = {},
        onMenuOptionClick = { option -> println("Menu option clicked: $option") },
        onAnswerClick = { isCorrect -> println("Answer clicked: $isCorrect") }
    )
}
