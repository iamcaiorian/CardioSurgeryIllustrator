package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.mock.student.mockModules
import com.example.cardiosurgeryillustrator.models.mock.student.mockQuizzes
import com.example.cardiosurgeryillustrator.ui.components.student.student.BottomBarStudent
import com.example.cardiosurgeryillustrator.ui.screens.student.auth.LoginScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.auth.RegisterScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.modules.ModuleVideoScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.favorite.FavoriteScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.modules.ModulesScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.modules.StudyScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.notification.HabitDetailScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.notification.NotificationSettingsScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.password_recovery.GenerateCodeScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.password_recovery.ValidateCodeScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.quiz.QuizScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.quiz.SecondQuizScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.password_recovery.ChangePasswordScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.quiz.QuizResultScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.settings_student.ProfileScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.settings_student.SettingsStudentScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.settings_student.SettingsValidateCodeScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.student.HomeStudentScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.subject.SubjectsScreen
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils

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

sealed class LoginFlow(val route: String) {
    object Login : LoginFlow("login_screen")
    object Register : LoginFlow("register_screen")
}

sealed class SubjectAction(val route: String) {
    object Modules : SubjectAction("modules")
    object ModulesVideo : SubjectAction("modulesVideo")
    object Study : SubjectAction("study")
    object Quiz : SubjectAction("quiz")
    object SecondQuiz : SubjectAction("secondQuiz")
}

sealed class SettingsAction(val route: String) {
    object Profile : SettingsAction("profile")
    object ValidateCode : SettingsAction("settingsValidateCode")
    object ChangePassword : SettingsAction("changePassword")
}

sealed class PasswordRecoveryAction(val route: String) {
    object GenerateCode : SettingsAction("generateCode")
    object ValidateCode : SettingsAction("validateCode")
    object ChangePassword : SettingsAction("changePassword")
}


@Composable
@ExperimentalMaterial3Api
fun StudentNavHost(
) {

    val studentNavController = rememberNavController()
    val context = LocalContext.current
    var startDestination by remember { mutableStateOf(LoginFlow.Login.route) }

    LaunchedEffect(Unit) {
        DataStoreUtils.readToken(context).collect { token ->
            if (!token.isNullOrEmpty()) {
                startDestination = BottomBarStudentAction.Home.route
            }
        }
    }


    val bottomBarRoutes = listOf(
        BottomBarStudentAction.Home.route,
        BottomBarStudentAction.Subject.route,
        BottomBarStudentAction.Favorites.route
    )

    NavHost(
        navController = studentNavController,
        startDestination = startDestination,
        enterTransition = {
            val fromIndex = bottomBarRoutes.indexOf(initialState.destination.route)
            val toIndex = bottomBarRoutes.indexOf(targetState.destination.route)
            if (toIndex > fromIndex) {
                // Navegação "para frente" (da direita para esquerda)
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            } else {
                // Navegação "para trás" (da esquerda para direita)
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            }
        },
        exitTransition = {
            val fromIndex = bottomBarRoutes.indexOf(initialState.destination.route)
            val toIndex = bottomBarRoutes.indexOf(targetState.destination.route)
            if (toIndex > fromIndex) {
                // Navegação "para frente" (saindo para a esquerda)
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            } else {
                // Navegação "para trás" (saindo para a direita)
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                )
            }
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = 300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = 300)
            )
        }
    ) {

        composable(LoginFlow.Login.route) {
            LoginScreen(
                onNavigateToHome = { studentNavController.navigate(BottomBarStudentAction.Home.route) },
                onForgotPasswordClick = { studentNavController.navigate(PasswordRecoveryAction.GenerateCode.route) },
                onRegisterClick = { studentNavController.navigate(LoginFlow.Register.route) }
            )
        }

        composable(LoginFlow.Register.route) {
            RegisterScreen(
                onRegisterSuccess = { studentNavController.navigate(LoginFlow.Login.route) }
            )
        }

        composable(PasswordRecoveryAction.GenerateCode.route) {
            GenerateCodeScreen(
                onNavigateBack = { studentNavController.popBackStack()},
                onNavigateToValidateCode = { email ->
                studentNavController.navigate(
                    "${PasswordRecoveryAction.ValidateCode.route}/$email"
                )
            })
        }

        composable(
            route = "${PasswordRecoveryAction.ValidateCode.route}/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->

            val email = backStackEntry.arguments?.getString("email")

            ValidateCodeScreen(
                email = email ?: "",
                onNavigateBack = { studentNavController.popBackStack() },
                onNavigateToChangePassword = { email, code ->
                    studentNavController.navigate(
                        "${PasswordRecoveryAction.ChangePassword.route}/$email/$code"
                    )
                }
            )
        }

        composable(
            route = "${PasswordRecoveryAction.ChangePassword.route}/{email}/{code}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("code") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val email = backStackEntry.arguments?.getString("email") ?: ""
            val code = backStackEntry.arguments?.getString("code") ?: ""

            Scaffold { innerPadding ->
                ChangePasswordScreen(
                    modifier = Modifier.padding(innerPadding),
                    email = email,
                    code = code,
                    onNavigateBack = { studentNavController.popBackStack() },
                    onNavigateToLogin = { studentNavController.navigate(LoginFlow.Login.route) }
                )
            }
        }


        composable(TopBarStudentAction.Settings.route) {
            Scaffold(
                bottomBar = { BottomBarStudent(navController = studentNavController) }
            ) { innerPadding ->
                SettingsStudentScreen(
                    navController = studentNavController,
                    modifier = Modifier.padding(innerPadding),
                    onNavigateBack = { studentNavController.popBackStack() }
                )
            }
        }

        composable("notifications") {
            NotificationSettingsScreen(
                onBackClick = { studentNavController.popBackStack() }
            )
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

        composable(SettingsAction.ValidateCode.route) {
            Scaffold { innerPadding ->
                SettingsValidateCodeScreen(
                    modifier = Modifier.padding(innerPadding),
                    onNavigateBack = { studentNavController.popBackStack() },
                    navController = studentNavController
                )
            }
        }



        composable(
            route = "${SubjectAction.Modules.route}/{subjectId}",
            arguments = listOf(navArgument("subjectId") { type = NavType.StringType })
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId")

            Scaffold(
                bottomBar = { BottomBarStudent(navController = studentNavController) }
            ) { innerPadding ->
                ModulesScreen(
                    navController = studentNavController,
                    modifier = Modifier.padding(innerPadding),
                    subjectId = subjectId ?: "unknown_id",
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
                    ModuleVideoScreen(
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
            route = "quiz/{quizId}",
            arguments = listOf(navArgument("quizId") { type = NavType.StringType })
        ) { backStackEntry ->
            val quizId = backStackEntry.arguments?.getString("quizId")

            QuizScreen(
                quizId = quizId ?: "1",
                onBackClick = { studentNavController.popBackStack() },
                onFinishQuiz = { correctAnswers, totalQuestions ->
                    studentNavController.navigate("quiz_result/$correctAnswers/$totalQuestions")
                }
            )
        }

        composable(
            route = "quiz_result/{correctAnswers}/{totalQuestions}",
            arguments = listOf(
                navArgument("correctAnswers") { type = NavType.IntType },
                navArgument("totalQuestions") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val correctAnswers = backStackEntry.arguments?.getInt("correctAnswers") ?: 0
            val totalQuestions = backStackEntry.arguments?.getInt("totalQuestions") ?: 0

            QuizResultScreen(
                correctAnswers = correctAnswers,
                totalQuestions = totalQuestions,
                onBackToHome = {
                    studentNavController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = "${SubjectAction.ModulesVideo.route}/{moduleId}",
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId")
            val module = mockModules.find { it.id == moduleId }

            Scaffold { innerPadding ->
                module?.let {
                    ModuleVideoScreen(
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

            Scaffold { innerPadding ->
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
            route = "habit_detail/{title}/{description}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Detalhes"
            val description =
                backStackEntry.arguments?.getString("description") ?: "Sem descrição disponível."

            HabitDetailScreen(
                title = title,
                description = description,
                onBackClick = { studentNavController.popBackStack() }
            )
        }
    }
}
