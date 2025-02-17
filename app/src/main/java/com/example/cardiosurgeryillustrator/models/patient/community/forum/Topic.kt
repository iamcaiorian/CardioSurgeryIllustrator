package com.example.cardiosurgeryillustrator.models.patient.community.forum

import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentRequest
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentResponse

data class Topic(
    val id: String,
    val userId: String,
    val theme: String,
    val title: String,
    val commentResponse: List<CommentResponse>,
    var likes: Int,
    var comments: Int,
    val timestamp: Long,
    var isLiked: Boolean = false,
    var isFavorite: Boolean = false
)
