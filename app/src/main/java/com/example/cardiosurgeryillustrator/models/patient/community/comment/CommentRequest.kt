package com.example.cardiosurgeryillustrator.models.patient.community.comment

data class CommentRequest(
    val forumId: String,
    val patientId: String,
    val content: String
)
