package com.example.cardiosurgeryillustrator.models.chatbot


data class ChatMessageRequest(
    val sender: String = "user",
    val message: String
)
