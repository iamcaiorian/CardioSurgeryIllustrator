package com.example.cardiosurgeryillustrator.models.patient.feedback

data class FeedbackRequest(
    val name: String,
    val email: String,
    val country: String,
    val state: String,
    val city: String,
    val feedback: String
)
