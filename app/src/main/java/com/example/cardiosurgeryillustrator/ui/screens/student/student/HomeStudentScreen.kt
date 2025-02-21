package com.example.cardiosurgeryillustrator.ui.screens.student.student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.navigation.SubjectAction
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.LastQuizCard
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.StudentQuizzes
import com.example.cardiosurgeryillustrator.ui.components.student.student.LastModuleCard
import com.example.cardiosurgeryillustrator.ui.components.student.student.StudentModules
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.utils.module.makeModuleEntityUtil
import com.example.cardiosurgeryillustrator.view_models.student.home.HomeStudentViewModel
import com.example.cardiosurgeryillustrator.view_models.student.home.HomeStudentViewModelFactory

@Composable
fun HomeStudentScreen(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val viewModel: HomeStudentViewModel = viewModel(
        factory = HomeStudentViewModelFactory(ModuleRepository(), context)
    )

    var lastModuleId by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadLastModuleOpenedId { moduleId ->
            lastModuleId = moduleId

            moduleId?.let { nonNullModuleId ->
                viewModel.getModuleById(nonNullModuleId)
            }
        }
    }

    val module = viewModel.module

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        if(module == null) {
            Column (
                modifier = Modifier.fillMaxWidth(),
                Arrangement.spacedBy(32.dp),
                Alignment.CenterHorizontally
            ){
                Text("Nenhum módulo foi aberto.", style = Typography.headlineMedium)
                Text("Nenhum Quiz foi aberto.", style = Typography.headlineMedium)
            }
        }

        module?.let { module ->
            LastModuleCard(
                module = makeModuleEntityUtil(module),
                onClick = { navController.navigate("${SubjectAction.Study.route}/${module.id}") }
            )

            LastQuizCard(quiz = module.quiz, onClick = { navController.navigate("quiz/${module.quiz.id}") })
        }


        StudentQuizzes()
        StudentModules()
    }
}


@Preview
@Composable
private fun HomeStudentScreenPreview() {
    HomeStudentScreen(navController = rememberNavController())

}
