package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import com.example.cardiosurgeryillustrator.ui.components.student.BottomBarStudent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.mock.StudyMock
import com.example.cardiosurgeryillustrator.models.mock.mockModules
import com.example.cardiosurgeryillustrator.models.mock.mockQuizzes
import com.example.cardiosurgeryillustrator.ui.screens.modules.ModulesScreen
import com.example.cardiosurgeryillustrator.ui.screens.modules.StudyScreen
import com.example.cardiosurgeryillustrator.ui.screens.quiz.QuizScreen
import com.example.cardiosurgeryillustrator.ui.screens.quiz.SecondQuizScreen
import com.example.cardiosurgeryillustrator.ui.screens.subject.SubjectsScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.HomeStudentScreen

sealed class TopBarStudentAction(
    val route: String,
    val icon: @Composable () -> Unit,
    val description: String
) {
    object Profile : TopBarStudentAction(
        route = "profile",
        icon = {
            androidx.compose.material3.Icon(
                Icons.Default.AccountCircle,
                contentDescription = "AccountCircle"
            )
        },
        description = "Profile"
    )

    object Search : TopBarStudentAction(
        route = "search",
        icon = {
            androidx.compose.material3.Icon(
                Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        description = "Search"
    )

    object Settings : TopBarStudentAction(
        route = "settings",
        icon = {
            androidx.compose.material3.Icon(
                Icons.Default.Settings,
                contentDescription = "Settings"
            )
        },
        description = "Settings"
    )
}

sealed class BottomBarStudentAction(
    val route: String,
    val icon: @Composable () -> Unit,
    val description: String
) {
    object Home : BottomBarStudentAction(
        route = "home",
        icon = { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = "Home") },
        description = "Home"
    )

    object Subject : BottomBarStudentAction(
        route = "subject",
        icon = {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.ic_note),
                contentDescription = "Subject",
                modifier = Modifier
                    .height(24.dp)
                    .width(20.dp)
                    .aspectRatio(1f)
            )
        },
        description = "Módulos"
    )

    object Favorites : BottomBarStudentAction(
        route = "favorites",
        icon = {
            androidx.compose.material3.Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = "Favorite"
            )
        },
        description = "Favorites"
    )
}

sealed class SubjectAction(val route: String) {
    object Modules : SubjectAction("modules")
    object Study : SubjectAction("study")
}


@Composable
fun StudentNavHost(
    onLogout: () -> Unit
) {
    val studentNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBarStudent(navController = studentNavController) }
    ) { innerPadding ->
        NavHost(
            navController = studentNavController,
            startDestination = BottomBarStudentAction.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomBarStudentAction.Home.route) {
                HomeStudentScreen(navController = studentNavController)
            }

            composable(BottomBarStudentAction.Subject.route) {
                SubjectsScreen(
                    navController = studentNavController,
                    onNavigateBack = { studentNavController.popBackStack() }
                )
            }

            composable(
                route = "${SubjectAction.Modules.route}/{subjectId}",
                arguments = listOf(navArgument("subjectId") { type = NavType.StringType })
            ) { backStackEntry ->
                val subjectId = backStackEntry.arguments?.getString("subjectId")
                val filteredModules = mockModules.filter { it.subjectId == subjectId }
                ModulesScreen(
                    navController = studentNavController,
                    modulesList = filteredModules,
                    onNavigateBack = { studentNavController.popBackStack() }
                )
            }

            composable(
                route = "${SubjectAction.Study.route}/{moduleId}",
                arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
            ) { backStackEntry ->
                val moduleId = backStackEntry.arguments?.getString("moduleId")
                val study = StudyMock.find { it.id == moduleId }
                study?.let {
                    StudyScreen(
                        moduleId = moduleId ?: "1",
                        onPreviousClick = { previousId ->
                            if (previousId != null) {
                                studentNavController.navigate("study/$previousId")
                            }
                        },
                        onNextClick = { nextId ->
                            if (nextId != null) {
                                studentNavController.navigate("study/$nextId")
                            }
                        },
                        onBackClick = { studentNavController.popBackStack() },
                        onMenuOptionClick = { println("Menu clicado: $it") }
                    )
                }
            }

            composable(
                route = "quiz/{quizId}",
                arguments = listOf(navArgument("quizId") { type = NavType.StringType })
            ) { backStackEntry ->
                val quizId = backStackEntry.arguments?.getString("quizId")
                val quiz = mockQuizzes.find { it.id == quizId }
                quiz?.let {
                    QuizScreen(
                        quiz = it,
                        onBackClick = { studentNavController.popBackStack() },
                        onMenuOptionClick = { println("Menu clicado") },
                        onAnswerClick = { isCorrect -> println("Resposta clicada: $isCorrect") },
                        onNavigateToSecondQuiz = {
                            studentNavController.navigate("secondQuiz/$quizId")
                        }
                    )
                }
            }

            composable(
                route = "secondQuiz/{quizId}",
                arguments = listOf(navArgument("quizId") { type = NavType.StringType })
            ) { backStackEntry ->
                val quizId = backStackEntry.arguments?.getString("quizId")
                val quiz = mockQuizzes.find { it.id == quizId }
                quiz?.let {
                    SecondQuizScreen(
                        quiz = it,
                        onBackClick = { studentNavController.popBackStack() },
                        onMenuOptionClick = { println("Menu clicado") },
                        onAnswerClick = { isCorrect -> println("Quiz concluído com resposta: $isCorrect") }
                    )
                }
            }
        }
    }
}
