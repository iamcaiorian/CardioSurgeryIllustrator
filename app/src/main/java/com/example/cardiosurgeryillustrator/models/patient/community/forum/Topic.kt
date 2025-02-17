package com.example.cardiosurgeryillustrator.models.patient.community.forum

import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentRequest

data class Topic(
    val id: String,
    val userId: String,
    val theme: String,
    val title: String,
    val commentRequests: List<CommentRequest>,
    var likes: Int,
    var comments: Int,
    val timestamp: Long,
    var isLiked: Boolean = false,
    var isFavorite: Boolean = false
)
