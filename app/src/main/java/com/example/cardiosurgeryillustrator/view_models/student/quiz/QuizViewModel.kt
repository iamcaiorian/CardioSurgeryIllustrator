package com.example.cardiosurgeryillustrator.view_models.student.quiz

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.core.services.QuizService
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz
import com.example.cardiosurgeryillustrator.models.student.quiz.QuizQuestion
import kotlinx.coroutines.launch

class QuizViewModel(private val quizService: QuizService) : ViewModel() {
    var quiz by mutableStateOf<Quiz?>(null)
        private set

    var currentQuestionIndex by mutableStateOf(0)
        private set

    val currentQuestion: QuizQuestion?
        get() = quiz?.questionEntityList?.getOrNull(currentQuestionIndex)

    fun loadQuiz(quizId: String) {
        viewModelScope.launch {
            try {
                val response = quizService.getQuiz(quizId)
                if (response.isSuccessful) {
                    quiz = response.body() // Corrige o erro de atribuição
                } else {
                    println("Erro ao carregar quiz: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun nextQuestion() {
        if (currentQuestionIndex < (quiz?.questionEntityList?.size ?: 0) - 1) {
            currentQuestionIndex++
        }
    }

    fun resetQuiz() {
        currentQuestionIndex = 0
    }
}
