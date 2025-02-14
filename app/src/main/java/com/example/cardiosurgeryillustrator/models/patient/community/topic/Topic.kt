package com.example.cardiosurgeryillustrator.models.patient.community.topic

import com.example.cardiosurgeryillustrator.models.patient.community.Message

data class Topic(
    val id: String,
    val userId: String,
    val theme: String,
    val title: String,
    val messages: List<Message>,
    var likes: Int,
    var comments: Int,
    val timestamp: Long,
    var isLiked: Boolean = false,
    var isFavorite: Boolean = false
)
