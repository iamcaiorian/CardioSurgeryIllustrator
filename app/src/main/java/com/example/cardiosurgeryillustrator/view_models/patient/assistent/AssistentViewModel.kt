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

    private val _messages = MutableStateFlow(listOf(
        ChatMessageRequest(
            sender = "assistant",
            message = "Olá! Sou o assistente virtual. Fique à vontade para perguntar!"
        )
    ))
    val messages: StateFlow<List<ChatMessageRequest>> = _messages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun sendMessage(userMessage: String) {
        if (_isLoading.value) return  // Evita múltiplos envios simultâneos

        val userChatMessageRequest = ChatMessageRequest(sender = "user", message = userMessage)

        _messages.value = _messages.value + userChatMessageRequest
        _isLoading.value = true  // Ativa o estado de carregamento

        viewModelScope.launch {
            try {
                val responses = assistantRepository.sendMessage(userChatMessageRequest)
                val responseMessages = responses.map {
                    ChatMessageRequest(sender = it.recipientId ?: "assistant", message = it.text)
                }
                _messages.value = _messages.value + responseMessages
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false  // Desativa o estado de carregamento
            }
        }
    }
}



