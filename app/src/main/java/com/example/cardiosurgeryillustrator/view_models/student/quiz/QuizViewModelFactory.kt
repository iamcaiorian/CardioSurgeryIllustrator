package com.example.cardiosurgeryillustrator.view_models.student.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.core.services.QuizService

class QuizViewModelFactory(private val quizService: QuizService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel(quizService) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido")
    }
}
