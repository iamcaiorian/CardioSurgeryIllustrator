package com.example.cardiosurgeryillustrator.view_models.patient.assistent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.patient.assistant.ChatMessageRequest
import com.example.cardiosurgeryillustrator.repository.patient.assistent.AssistantRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AssistantViewModel(
    private val assistantRepository: AssistantRepository
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessageRequest>>(listOf(
        ChatMessageRequest(
            sender = "assistant",
            message = "Olá! Sou o assistente virtual. Fique à vontade para perguntar!"
        )
    ))
    val messages: StateFlow<List<ChatMessageRequest>> = _messages

    fun sendMessage(userMessage: String) {
        val userChatMessageRequest = ChatMessageRequest(sender = "user", message = userMessage)


        _messages.value = _messages.value + userChatMessageRequest


        viewModelScope.launch {
            try {
                val responses = assistantRepository.sendMessage(userChatMessageRequest)


                val responseMessages = responses.map {
                    ChatMessageRequest(sender = it.recipientId ?: "assistant", message = it.text)
                }
                _messages.value = _messages.value + responseMessages
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}


