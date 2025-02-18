package com.example.cardiosurgeryillustrator.view_models.admin.quiz_modules


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.admin.question.QuestionRepository
import com.example.cardiosurgeryillustrator.repository.quiz.QuizRepository
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository

class QuizViewModelFactory(
    private val quizRepository: QuizRepository,
    private val questionRepository: QuestionRepository,
    private val moduleRepository: ModuleRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuizViewModel(quizRepository, questionRepository, moduleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}