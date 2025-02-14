package com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.Quiz
import com.example.cardiosurgeryillustrator.models.student.quiz.question.CreateQuestionRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.question.QuestionResponse
import com.example.cardiosurgeryillustrator.models.student.module.ModuleResponse
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.CreateQuizRequest
import com.example.cardiosurgeryillustrator.repository.admin.question.QuestionRepository
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuizViewModel(
    private val quizRepository: QuizRepository,
    private val questionRepository: QuestionRepository,
    private val moduleRepository: ModuleRepository
) : ViewModel() {

    private val _quizzes = MutableStateFlow<List<Quiz>>(emptyList())
    val quizzes: StateFlow<List<Quiz>> get() = _quizzes

    private val _modules = MutableStateFlow<List<ModuleResponse>>(emptyList())
    val modules: StateFlow<List<ModuleResponse>> get() = _modules

    fun loadQuizzes() {
        viewModelScope.launch {
            try {
                val response = quizRepository.getQuizzes()
                if (response.isSuccessful) {
                    _quizzes.value = response.body() ?: emptyList()
                    println("Quizzes carregados com sucesso: ${_quizzes.value}")
                } else {
                    println("Erro ao carregar quizzes: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("Erro ao conectar à API: ${e.message}")
            }
        }
    }

    fun loadModules() {
        viewModelScope.launch {
            try {
                val modulesList = moduleRepository.getAllModules()
                _modules.value = modulesList
                println("Módulos carregados com sucesso: $_modules")
            } catch (e: Exception) {
                println("Erro ao carregar módulos: ${e.message}")
            }
        }
    }

    fun createQuiz(
        title: String,
        description: String,
        problem: String,
        alternativeA: String,
        alternativeB: String,
        alternativeC: String,
        alternativeD: String,
        answer: String,
        moduleId: String,
        onSuccess: (Quiz) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                // Primeiro cria a questão
                val questionRequest = CreateQuestionRequest(
                    problem = problem,
                    alternativeA = alternativeA,
                    alternativeB = alternativeB,
                    alternativeC = alternativeC,
                    alternativeD = alternativeD,
                    answer = answer
                )
                val questionResponse: QuestionResponse = questionRepository.createQuestion(questionRequest)

                // Se a questão foi criada com sucesso, cria o quiz usando a questão retornada
                val quizData = CreateQuizRequest(
                    title = title,
                    description = description,
                    moduleId = moduleId,
                    questionId = questionResponse.id
                )

                val response = quizRepository.createQuiz(quizData)
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    } ?: onError("Erro: Resposta do servidor está vazia")
                } else {
                    onError("Erro ao criar quiz: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                onError("Erro ao conectar: ${e.message}")
            }
        }
    }
}
