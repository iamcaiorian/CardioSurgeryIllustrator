package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.student.quiz.question.CreateQuestionRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.question.QuestionResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface QuestionService {
    @POST("/question/create")
    suspend fun createQuestion(@Body question: CreateQuestionRequest): QuestionResponse
}