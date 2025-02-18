package com.example.cardiosurgeryillustrator.ui.screens.student.modules

import ErrorButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.mock.student.StudyMock
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import com.example.cardiosurgeryillustrator.ui.components.button.SuccessButton
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.TopBarQuiz
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.view_models.student.module.ModulesViewModel
import com.example.cardiosurgeryillustrator.view_models.student.module.ModulesViewModelFactory
import com.example.cardiosurgeryillustrator.view_models.student.study.StudyViewModel
import com.example.cardiosurgeryillustrator.view_models.student.study.StudyViewModelFactory
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
    val moduleRepository = ModuleRepository()
    val viewModel: StudyViewModel = viewModel(
        factory = StudyViewModelFactory(moduleRepository)
    )

    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(moduleId) {
        isLoading = true
        viewModel.getModuleById(moduleId)
        isLoading = false
    }

    val module = viewModel.module

    if (isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        module?.let {
            Scaffold(
                topBar = {
                    TopBarQuiz(
                        title = module.title,
                        subtitle = module.description,
                        onBackClick = onBackClick,
                        onMenuOptionClick = onMenuOptionClick
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = module.title,
                        style = Typography.titleMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = module.longDescription,
                        style = Typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center
                    )


                    Image(
                        painter = painterResource(id = R.drawable.anatomia_coracao),
                        contentDescription = "Imagem do ${module.title}",
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
                                val previousId = module.id.toIntOrNull()?.minus(1)?.toString()
                                onPreviousClick(previousId)
                            }
                        )
                        SuccessButton(
                            text = "Próximo",
                            onClick = {
                                onNextClick(module.quiz.id ?: "unknown quiz")
                            }
                        )
                    }
                }
            }
        } ?: run {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Erro ao carregar o módulo.", style = MaterialTheme.typography.bodyLarge)
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
