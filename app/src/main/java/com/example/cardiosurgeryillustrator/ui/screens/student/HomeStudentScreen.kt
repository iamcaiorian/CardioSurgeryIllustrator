package com.example.cardiosurgeryillustrator.ui.screens.student

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.models.mock.mockModules
import com.example.cardiosurgeryillustrator.navigation.SubjectAction
import com.example.cardiosurgeryillustrator.ui.components.student.LastModuleCard
import com.example.cardiosurgeryillustrator.ui.components.student.LastQuizCard
import com.example.cardiosurgeryillustrator.ui.components.student.StudentModules
import com.example.cardiosurgeryillustrator.ui.components.student.StudentQuizzes
import com.example.cardiosurgeryillustrator.ui.components.student.TopBarStudent

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
                onClick = { navController.navigate("${SubjectAction.Study.route}/${mockModules[1].id}") })
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
