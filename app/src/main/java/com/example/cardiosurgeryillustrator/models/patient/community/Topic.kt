package com.example.cardiosurgeryillustrator.models.patient.community;

data class Topic(
    val id: String,
    val user: User,
    val theme: String,
    val title: String,
    var likes: Int,
    var comments: Int,
    val timestamp: Long
)