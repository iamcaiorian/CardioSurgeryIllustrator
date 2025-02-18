package com.example.cardiosurgeryillustrator.navigation

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.repository.admin.question.QuestionRepository
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import com.example.cardiosurgeryillustrator.ui.components.admin.admin.BottomBarAdmin
import com.example.cardiosurgeryillustrator.ui.components.student.student.BottomBarStudent
import com.example.cardiosurgeryillustrator.ui.components.topBar.StandardTopBar
import com.example.cardiosurgeryillustrator.ui.screens.admin.admin_modules.AdminModulesScreen
import com.example.cardiosurgeryillustrator.ui.screens.admin.create_module.CreateModuleScreen
import com.example.cardiosurgeryillustrator.ui.screens.admin.create_quiz.AdminAddQuizScreen
import com.example.cardiosurgeryillustrator.ui.screens.admin.create_quiz.ListQuizScreen
import com.example.cardiosurgeryillustrator.ui.screens.admin.faq.FAQScreen
import com.example.cardiosurgeryillustrator.ui.screens.admin.home.HomeAdminScreen
import com.example.cardiosurgeryillustrator.ui.screens.admin.login.LoginAdminScreen
import com.example.cardiosurgeryillustrator.ui.screens.admin.quiz.addQuestionToQuiz.QuestionQuiz
import com.example.cardiosurgeryillustrator.ui.screens.student.quiz.QuizResultScreen
import com.example.cardiosurgeryillustrator.ui.screens.student.student.HomeStudentScreen
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules.QuizViewModel
import com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules.QuizViewModelFactory

sealed class LoginAdminFlow(val route: String) {
    object LoginAdmin : LoginAdminFlow("login_admin")
}

sealed class BottomBarAdminAction(
    val route: String,
    val icon: @Composable () -> Unit,
    val description: String
) {
    object Home : BottomBarAdminAction(
        route = "home",
        icon = { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = "Home") },
        description = "Home"
    )

    object Quiz : BottomBarAdminAction(
        route = "quiz",
        icon = { androidx.compose.material3.Icon(Icons.Filled.Menu, contentDescription = "Quiz") },
        description = "Quiz"
    )

    object Modules : BottomBarAdminAction(
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

    object FAQ : BottomBarAdminAction(
        route = "faq",
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
        description = "FAQ"
    )
}

@Composable
@ExperimentalMaterial3Api
fun AdminNavHost() {
    val adminNavController = rememberNavController()
    val context = LocalContext.current

    val quizRepository = QuizRepository(context)
    val questionRepository = QuestionRepository()
    val moduleRepository = ModuleRepository()

    val quizViewModel =
        QuizViewModelFactory(quizRepository, questionRepository, moduleRepository).create(
            QuizViewModel::class.java
        )

    var startDestination by remember { mutableStateOf(LoginAdminFlow.LoginAdmin.route) }

    // Verifica se o token está salvo no DataStore
    LaunchedEffect(Unit) {
        DataStoreUtils.readTokenAdmin(context).collect { token ->
            if (!token.isNullOrEmpty()) {
                startDestination = BottomBarAdminAction.Home.route
            }
        }
        quizViewModel.loadQuizzes()
    }

    NavHost(
        navController = adminNavController,
        startDestination = startDestination
    ) {
        composable(LoginAdminFlow.LoginAdmin.route) {
            Scaffold { innerPadding ->
                LoginAdminScreen(
                    modifier = Modifier.padding(innerPadding),
                    onNavigateToHome = { adminNavController.navigate(BottomBarAdminAction.Home.route)}
                )
            }

        }

        composable("create_module") {
            Scaffold { innerPadding ->
                CreateModuleScreen(
                    modifier = Modifier.padding(innerPadding),
                    onNavigateBack = { adminNavController.popBackStack() },
                    onNavigateToModules = { adminNavController.popBackStack() }
                )
            }

        }

        composable(BottomBarAdminAction.Home.route) {
            Scaffold(
                bottomBar = { BottomBarAdmin(navController = adminNavController) }
            ) { innerPadding ->
                HomeAdminScreen(
                    navController = adminNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable(BottomBarAdminAction.Modules.route) {
            Scaffold(
                bottomBar = { BottomBarAdmin(navController = adminNavController) },
                topBar = {
                    StandardTopBar(
                        modifier = Modifier,
                        onNavigateBack = { adminNavController.popBackStack() },
                        title = "Módulos"
                    )
                }
            ) { innerPadding ->
                AdminModulesScreen(
                    modifier = Modifier.padding(innerPadding),
                    onNavigateToCreateModule = { adminNavController.navigate("create_module") }
                )
            }
        }


        composable(BottomBarAdminAction.Quiz.route) {
            Scaffold(
                bottomBar = { BottomBarAdmin(navController = adminNavController) },
                topBar = {
                    StandardTopBar(
                        modifier = Modifier,
                        title = "Quizzes",
                        onNavigateBack = { adminNavController.popBackStack() }
                    )
                }
            ) { innerPadding ->
                val quizzes by quizViewModel.quizzes.collectAsState()
                ListQuizScreen(
                    quizzes = quizzes,
                    onEditQuiz = { quiz ->
                        adminNavController.navigate("add_question_to_quiz/${quiz.id}")
                    },
                    onDeleteQuiz = {},
                    onCreateQuiz = {
                        adminNavController.navigate("create_quiz")
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
        composable("add_question_to_quiz/{quizId}") { backStackEntry ->
            val quizId = backStackEntry.arguments?.getString("quizId") ?: return@composable

            QuestionQuiz(
                quizId = quizId,
                onQuestionAdded = { addedQuestion ->
                    println("Questão adicionada ao quiz com sucesso: ${addedQuestion.id}")
                    adminNavController.popBackStack()
                }
            )
        }

        composable("create_quiz") {
            Scaffold(
                topBar = {
                    StandardTopBar(
                        title = "Criar Quiz",
                        onNavigateBack = { adminNavController.popBackStack() }
                    )
                }
            ) { innerPadding ->
                AdminAddQuizScreen(
                    onQuizAdded = {
                        quizViewModel.loadQuizzes()
                        adminNavController.popBackStack()
                    },
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }

        composable(BottomBarAdminAction.FAQ.route) {
            Scaffold(
                bottomBar = { BottomBarAdmin(navController = adminNavController) },
                topBar = {
                    StandardTopBar(
                        modifier = Modifier,
                        onNavigateBack = { adminNavController.popBackStack() },
                        title = "FAQ"
                    )
                }
            ) { innerPadding ->
                FAQScreen(
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}