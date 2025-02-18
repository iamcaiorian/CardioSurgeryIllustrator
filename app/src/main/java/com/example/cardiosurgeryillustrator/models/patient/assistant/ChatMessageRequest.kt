package com.example.cardiosurgeryillustrator.models.patient.assistant


data class ChatMessageRequest(
    val sender: String = "user",
    val message: String
)
