package com.example.cardiosurgeryillustrator.repository.patient.assistent

import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.patient.assistant.ChatMessageRequest
import com.example.cardiosurgeryillustrator.models.patient.assistant.ChatMessageResponse

class AssistantRepository {
    suspend fun sendMessage(chatMessageRequest: ChatMessageRequest): List<ChatMessageResponse> {
        return RetrofitInstance.assistantService.sendMessage(chatMessageRequest)
    }
}