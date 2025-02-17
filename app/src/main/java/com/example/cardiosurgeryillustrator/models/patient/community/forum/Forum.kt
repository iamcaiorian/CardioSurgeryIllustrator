package com.example.cardiosurgeryillustrator.models.patient.community.forum

import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentResponse
import java.time.LocalDateTime

data class Forum(
    val id: String,
    val userId: String,
    val theme: String,
    val title: String,
    val commentResponse: List<CommentResponse>,
    var likes: Int,
    var comments: Int,
    val timestamp: LocalDateTime,
    var isLiked: Boolean = false,
    var isFavorite: Boolean = false
)
