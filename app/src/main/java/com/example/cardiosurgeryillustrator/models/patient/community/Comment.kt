package com.example.cardiosurgeryillustrator.models.patient.community;

data class Comment(
    val id: String,
    val topicId: String,
    val commentId: String,
    val userId: String,
    val content: String,
    val likes: Int,
    val isCommentable: Boolean,
    val timestamp: Long
)