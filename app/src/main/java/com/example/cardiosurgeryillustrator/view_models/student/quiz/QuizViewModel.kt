package com.example.cardiosurgeryillustrator.view_models.student.quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.Quiz
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import kotlinx.coroutines.launch

class QuizViewModel(private val quizRepository: QuizRepository): ViewModel() {
    var quiz: Quiz? by mutableStateOf(null)

    fun getQuizById(quizId: String) {
        viewModelScope.launch {
            try {
                quiz = quizRepository.getQuizById(quizId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}