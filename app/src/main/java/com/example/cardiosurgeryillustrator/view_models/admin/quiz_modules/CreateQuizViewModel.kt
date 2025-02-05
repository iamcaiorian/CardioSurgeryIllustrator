package com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.quiz.CreateQuizRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class QuizViewModel(
    private val quizRepository: QuizRepository
) : ViewModel() {

    private val _quizzes = MutableStateFlow<List<Quiz>>(emptyList())
    val quizzes: StateFlow<List<Quiz>> get() = _quizzes

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
}

class CreateQuizViewModel(
    private val quizRepository: QuizRepository
) : ViewModel() {

    var createQuizResponse: Response<Quiz>? = null
        private set

    fun createQuiz(
        quiz: CreateQuizRequest,
        onSuccess: (CreateQuizRequest) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                println("Tentando criar quiz..." + quiz)
                val response = quizRepository.createQuiz(quiz)
                println("Resposta do servidor: ${response.body()}")
                if (response.isSuccessful) {
                    response.body()?.let {
                        createQuizResponse = response
                        onSuccess(quiz) // Notificar sucesso
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