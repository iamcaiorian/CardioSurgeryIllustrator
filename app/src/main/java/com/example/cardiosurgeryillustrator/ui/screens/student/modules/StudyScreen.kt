package com.example.cardiosurgeryillustrator.ui.screens.student.modules

import ErrorButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import com.example.cardiosurgeryillustrator.ui.components.button.SuccessButton
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.TopBarQuiz
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Typography
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
        factory = StudyViewModelFactory(moduleRepository, context = LocalContext.current)
    )

    var isLoading by remember { mutableStateOf(true) }
    val module by viewModel.module.collectAsState()

    LaunchedEffect(moduleId) {
        isLoading = true
        viewModel.saveLastModuleOpenedId(moduleId)
        viewModel.getModuleById(moduleId)
    }

    if (module != null) {
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopBarQuiz(
                title = module?.title ?: "Carregando...",
                subtitle = module?.description ?: "",
                onBackClick = onBackClick,
                onMenuOptionClick = onMenuOptionClick
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
            module != null -> {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = module!!.title,
                        style = Typography.titleMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = module!!.longDescription,
                        style = Typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center
                    )

                    Image(
                        painter = painterResource(id = R.drawable.anatomia_coracao),
                        contentDescription = "Imagem do ${module!!.title}",
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
                                val previousId = module!!.id.toIntOrNull()?.minus(1)?.toString()
                                onPreviousClick(previousId)
                            }
                        )
                        SuccessButton(
                            text = "Próximo",
                            onClick = {
                                onNextClick(module!!.quiz.id ?: "unknown quiz")
                            }
                        )
                    }
                }
            }
            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Erro ao carregar o módulo.", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
