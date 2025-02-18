package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.patient.assistant.ChatMessageRequest
import com.example.cardiosurgeryillustrator.models.patient.assistant.ChatMessageResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AssistantService {
    @POST("/webhooks/rest/webhook")
    suspend fun sendMessage(@Body chatMessageRequest: ChatMessageRequest): List<ChatMessageResponse>
}