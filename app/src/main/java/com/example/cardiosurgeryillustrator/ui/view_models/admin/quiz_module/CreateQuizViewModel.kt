package com.example.cardiosurgeryillustrator.ui.view_models.admin.quiz_module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.quiz.CreateQuizRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import kotlinx.coroutines.launch
import retrofit2.Response

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
