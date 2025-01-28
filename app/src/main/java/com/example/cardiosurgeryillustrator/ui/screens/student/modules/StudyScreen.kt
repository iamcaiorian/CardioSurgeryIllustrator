package com.example.cardiosurgeryillustrator.ui.screens.student.modules

import ErrorButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.mock.student.StudyMock
import com.example.cardiosurgeryillustrator.ui.components.button.SuccessButton
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.TopBarQuiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyScreen(
    moduleId: String,
    onPreviousClick: (String?) -> Unit,
    onNextClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onMenuOptionClick: (String) -> Unit
) {
    val study = StudyMock.find { it.moduleId == moduleId }

    if (study == null) {
        Text("Estudo não encontrado")
        return
    }

    Scaffold(
        topBar = {
            TopBarQuiz(
                title = study.title,
                subtitle = "Assunto ${study.moduleId}",
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
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = study.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = study.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Image(
                painter = painterResource(id = R.drawable.anatomia_coracao),
                contentDescription = "Imagem do ${study.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(vertical = 24.dp),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ErrorButton(
                    text = "Anterior",
                    onClick = {
                        val previousId = (study.id.toIntOrNull()?.minus(1))?.toString()
                        onPreviousClick(previousId)
                    }
                )
                SuccessButton(
                    text = "Próximo",
                    onClick = {
                        onNextClick(study.moduleId)
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StudyScreenPreview() {
    StudyScreen(
        moduleId = "1",
        onPreviousClick = { println("Anterior clicado: $it") },
        onNextClick = { println("Próximo clicado: $it") },
        onBackClick = { println("Voltar clicado") },
        onMenuOptionClick = { option -> println("Menu clicado: $option") }
    )
}
