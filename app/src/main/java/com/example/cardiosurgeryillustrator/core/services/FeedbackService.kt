package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.patient.feedback.FeedbackRequest
import com.example.cardiosurgeryillustrator.models.patient.feedback.FeedbackResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface FeedbackService {
    @POST("/api/feedback")
    suspend fun saveFeedback(@Body feedback: FeedbackRequest): FeedbackResponse
}