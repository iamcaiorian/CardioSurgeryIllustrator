package com.example.cardiosurgeryillustrator.ui.screens.student.student

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.models.mock.mockModules
import com.example.cardiosurgeryillustrator.navigation.SubjectAction
import com.example.cardiosurgeryillustrator.ui.components.student.student.LastModuleCard
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.LastQuizCard
import com.example.cardiosurgeryillustrator.ui.components.student.student.StudentModules
import com.example.cardiosurgeryillustrator.ui.components.student.quiz.StudentQuizzes
import com.example.cardiosurgeryillustrator.ui.components.student.student.TopBarStudent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeStudentScreen(navController: NavController, modifier: Modifier = Modifier) {


    Scaffold(topBar = { TopBarStudent(navController = navController) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LastModuleCard(module = mockModules[4],
                onClick = { navController.navigate("${SubjectAction.ModulesVideo.route}/${mockModules[1].id}") })
            LastQuizCard(module = mockModules[1], onClick = {})
            StudentQuizzes()
            StudentModules()
        }
    }
}

@Preview
@Composable
private fun HomeStudentScreenPreview() {
    HomeStudentScreen(navController = rememberNavController())

}
