package com.example.cardiosurgeryillustrator.view_models.student.quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.Quiz
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel(private val quizRepository: QuizRepository): ViewModel() {
    private val _quiz = MutableStateFlow<Quiz?>(null)
    val quiz = _quiz.asStateFlow()

    fun getQuizById(quizId: String) {
        viewModelScope.launch {
            try {
                val loadedQuiz = quizRepository.getQuizById(quizId)
                _quiz.update { loadedQuiz }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}