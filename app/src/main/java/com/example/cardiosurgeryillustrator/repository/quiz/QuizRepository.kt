package com.example.cardiosurgeryillustrator.repository.quiz

import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.student.quiz.CreateQuizRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz
import retrofit2.Response


class QuizRepository() {
    suspend fun createQuiz(quiz: CreateQuizRequest): Response<Quiz> {
        return RetrofitInstance.quizService.createQuiz(quiz)
    }
}
