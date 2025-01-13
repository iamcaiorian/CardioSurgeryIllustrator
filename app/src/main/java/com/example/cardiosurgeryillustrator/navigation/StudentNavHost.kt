package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import com.example.cardiosurgeryillustrator.ui.screens.modules.ModuleVideoScreen
import com.example.cardiosurgeryillustrator.ui.screens.favorite.FavoriteScreen
import com.example.cardiosurgeryillustrator.ui.screens.modules.ModulesScreen
import com.example.cardiosurgeryillustrator.ui.screens.modules.StudyScreen
import com.example.cardiosurgeryillustrator.ui.screens.quiz.QuizScreen
import com.example.cardiosurgeryillustrator.ui.screens.quiz.SecondQuizScreen
import com.example.cardiosurgeryillustrator.ui.screens.settings.ProfileScreen
import com.example.cardiosurgeryillustrator.ui.screens.subject.SubjectsScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.HomeStudentScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.SettingsStudentScreen

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
                Icons.Default.Favorite,
                contentDescription = "Favorite"
            )
        },
        description = "Favoritos"
    )
}

sealed class SubjectAction(val route: String) {
    object Modules : SubjectAction("modules")
    object ModulesVideo : SubjectAction("modulesVideo")
    object Study : SubjectAction("study")
    object Quiz : SubjectAction("quiz")
    object SecondQuiz : SubjectAction("secondQuiz")
}

sealed class SettingsAction(val route: String) {
    object Notifications : SettingsAction("notifications")
    object Profile : SettingsAction("profile")
}


@Composable
@ExperimentalMaterial3Api
fun StudentNavHost(
    onLogout: () -> Unit
) {
    val studentNavController = rememberNavController()

    NavHost(
        navController = studentNavController,
        startDestination = BottomBarStudentAction.Home.route
    ) {
        composable(TopBarStudentAction.Settings.route) {
            Scaffold (
                bottomBar = { BottomBarStudent(navController = studentNavController) }
            ){innerPadding ->
                SettingsStudentScreen(
                    navController = studentNavController,
                    modifier = Modifier.padding(innerPadding),
                    onNavigateBack = { studentNavController.popBackStack() },
                )
            }
        }

        composable(BottomBarStudentAction.Home.route) {
            Scaffold(
                bottomBar = { BottomBarStudent(navController = studentNavController) }
            ) { innerPadding ->
                HomeStudentScreen(
                    navController = studentNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable(BottomBarStudentAction.Subject.route) {
            Scaffold(
                bottomBar = { BottomBarStudent(navController = studentNavController) }
            ) { innerPadding ->
                SubjectsScreen(
                    navController = studentNavController,
                    modifier = Modifier.padding(innerPadding),
                    onNavigateBack = { studentNavController.popBackStack() }
                )
            }
        }

        composable(BottomBarStudentAction.Favorites.route) {
            Scaffold(
                bottomBar = { BottomBarStudent(navController = studentNavController) }
            ) { innerPadding ->
                FavoriteScreen(
                    navController = studentNavController,
                    modulesList = mockModules,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable(SettingsAction.Profile.route) {
            Scaffold(
                bottomBar = { BottomBarStudent(navController = studentNavController) }
            ) { innerPadding ->
                ProfileScreen(
                    modifier = Modifier.padding(innerPadding),
                    onNavigateBack = { studentNavController.popBackStack() },
                )
            }
        }

        composable(
            route = "${SubjectAction.Modules.route}/{subjectId}",
            arguments = listOf(navArgument("subjectId") { type = NavType.StringType })
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId")
            val filteredModules = mockModules.filter { it.subjectId == subjectId }

            Scaffold(
                bottomBar = { BottomBarStudent(navController = studentNavController) }
            ) { innerPadding ->
                ModulesScreen(
                    navController = studentNavController,
                    modulesList = filteredModules,
                    modifier = Modifier.padding(innerPadding),
                    onNavigateBack = { studentNavController.popBackStack() }
                )
            }
        }

        composable(
            route = "${SubjectAction.ModulesVideo.route}/{moduleId}",
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId")
            val module = mockModules.find { it.id == moduleId }

            Scaffold { innerPadding ->
                module?.let {
                    ModuleVideoScreen (
                        module = module,
                        modifier = Modifier.padding(innerPadding),
                        onBackClick = { studentNavController.popBackStack() },
                        onMenuOptionClick = { println("Menu clicado: $it") },
                        navController = studentNavController
                    )

                }
            }
        }

        composable(
            route = "${SubjectAction.Study.route}/{moduleId}",
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId")
            val study = StudyMock.find { it.moduleId == moduleId }

            Scaffold { innerPadding ->
                study?.let {
                    StudyScreen(
                        moduleId = moduleId ?: "1",
                        modifier = Modifier.padding(innerPadding),
                        onPreviousClick = { previousId ->
                            if (previousId != null) {
                                studentNavController.navigate("${SubjectAction.Study.route}/$previousId")
                            }
                        },
                        onNextClick = { nextId ->
                            studentNavController.navigate("${SubjectAction.Quiz.route}/$nextId")
                        },
                        onBackClick = { studentNavController.popBackStack() },
                        onMenuOptionClick = { println("Menu clicado: $it") }
                    )
                }
            }
        }

        composable(
            route = "${SubjectAction.Quiz.route}/{moduleId}",
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId")
            val quiz = mockQuizzes.find { it.id == moduleId }

            Scaffold { innerPadding ->
                quiz?.let {
                    QuizScreen(
                        quiz = it,
                        modifier = Modifier.padding(innerPadding),
                        onBackClick = { studentNavController.popBackStack() },
                        onMenuOptionClick = { println("Menu clicado") },
                        onAnswerClick = { isCorrect ->
                            println("Resposta clicada: $isCorrect")
                        },
                        onNavigateToSecondQuiz = {
                            studentNavController.navigate("${SubjectAction.SecondQuiz.route}/$moduleId")
                        }
                    )
                }
            }
        }

        composable(
            route = "${SubjectAction.SecondQuiz.route}/{moduleId}",
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId")
            val quiz = mockQuizzes.find { it.id == moduleId }

            Scaffold { innerPadding ->
                quiz?.let {
                    SecondQuizScreen(
                        quiz = it,
                        modifier = Modifier.padding(innerPadding),
                        onBackClick = { studentNavController.popBackStack() }
                    )
                }
            }
        }
    }
}

