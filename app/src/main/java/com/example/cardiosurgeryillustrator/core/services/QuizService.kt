package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.student.quiz.question.Question
import com.example.cardiosurgeryillustrator.models.student.quiz.question.QuestionResponse
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.CreateQuizRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.Quiz
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface QuizService {
    @POST("/quiz/create")
    suspend fun createQuiz(
        @Body quiz: CreateQuizRequest,
        @Header("Authorization") token: String
    ): Response<Quiz>

    @GET("/quiz/get-all")
    suspend fun getQuizzes(@Header("Authorization") token: String): Response<List<Quiz>>

    @GET("/quiz/get-one/{quiz_id}")

    suspend fun getQuizById(
        @Path("quiz_id") quizId: String,
        @Header("Authorization") token: String
    ): Quiz

    @PUT("/quiz/add-question/{questionId}/{quizId}")
    suspend fun addQuestionToQuiz(@Path("questionId") questionId: String, @Path("quizId") quizId: String): Response<QuestionResponse>
}

