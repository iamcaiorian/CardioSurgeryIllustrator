package com.example.cardiosurgeryillustrator.ui.screens.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.example.cardiosurgeryillustrator.models.Quiz
import com.example.cardiosurgeryillustrator.models.mock.mockQuizzes
import com.example.cardiosurgeryillustrator.ui.components.button.ConfirmationButton
import com.example.cardiosurgeryillustrator.ui.components.button.QuestionsButton
import com.example.cardiosurgeryillustrator.ui.components.topBar.TopBarQuiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondQuizScreen(
    quiz: Quiz,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBarQuiz(
                title = quiz.title,
                subtitle = quiz.subtitle,
                onBackClick = onBackClick,
                onMenuOptionClick = {}
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
            Image(
                painter = painterResource(id = R.drawable.coracao_icon),
                contentDescription = "Imagem do coração",
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = quiz.question,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                quiz.options?.forEach { option ->
                    QuestionsButton(
                        text = option,
                        isSelected = false,
                        onClick = { }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            ConfirmationButton(
                text = "Confirmar",
                onClick = { }
            )
        }
    }
}

