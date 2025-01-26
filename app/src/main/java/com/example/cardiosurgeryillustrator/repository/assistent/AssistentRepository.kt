package com.example.cardiosurgeryillustrator.repository.assistent

import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.chatbot.ChatMessageRequest
import com.example.cardiosurgeryillustrator.models.chatbot.ChatMessageResponse

class AssistentRepository {
    suspend fun sendMessage(chatMessageRequest: ChatMessageRequest): List<ChatMessageResponse> {
        return RetrofitInstance.assistentService.sendMessage(chatMessageRequest)
    }
}