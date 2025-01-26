package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.chatbot.ChatMessageRequest
import com.example.cardiosurgeryillustrator.models.chatbot.ChatMessageResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AssistentService {
    @POST("/webhooks/rest/webhook")
    suspend fun sendMessage(@Body chatMessageRequest: ChatMessageRequest): List<ChatMessageResponse>
}