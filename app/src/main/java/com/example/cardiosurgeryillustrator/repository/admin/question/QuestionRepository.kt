package com.example.cardiosurgeryillustrator.repository.admin.question

import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.student.quiz.question.CreateQuestionRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.question.QuestionResponse

class QuestionRepository {
    suspend fun createQuestion(createQuestionRequest: CreateQuestionRequest): QuestionResponse {
        return RetrofitInstance.questionService.createQuestion(createQuestionRequest)
    }
}