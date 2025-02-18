package com.example.cardiosurgeryillustrator.ui.screens.student.modules

import ErrorButton
import android.net.Uri
import android.widget.VideoView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.mock.student.mockModules
import com.example.cardiosurgeryillustrator.models.mock.student.mockSubjects
import com.example.cardiosurgeryillustrator.navigation.SubjectAction
import com.example.cardiosurgeryillustrator.ui.components.button.SuccessButton
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.TopBarQuiz
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuleVideoScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    module: Module,
    onBackClick: () -> Unit,
    onMenuOptionClick: (String) -> Unit,
) {
    val subject = mockSubjects.find { it.id == module.subjectId }
    val moduleId = module

    Scaffold(
        topBar = {
            TopBarQuiz(
                title = module.title,
                subtitle = subject?.title,
                onBackClick = onBackClick,
                onMenuOptionClick = onMenuOptionClick,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = module.title,
                    style = Typography.headlineLarge
                )
            }

            Text(text = module.longDescription, style = Typography.bodyMedium)

            AndroidView(
                factory = { ctx ->
                    VideoView(ctx).apply {
                        setVideoURI(Uri.parse("android.resource://${context.packageName}/raw/heart_video"))
                        start()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ErrorButton(
                    text = "Voltar",
                    onClick = onBackClick
                )
                SuccessButton(
                    text = "Continuar",
                    onClick = {
                        navController.navigate("${SubjectAction.Study.route}/${module.id}")
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun QuizVideoPreview() {
    ModuleVideoScreen(
        module = mockModules[1],
        onBackClick = {},
        onMenuOptionClick = {},
        navController = rememberNavController())
}