package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.student.quiz.CreateQuizRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuizService {
    @POST("/quiz/create")
    suspend fun createQuiz(@Body quiz: CreateQuizRequest): Response<Quiz>

    @GET("/quiz/get-all")
    suspend fun getQuizzes(): Response<List<Quiz>>
}
