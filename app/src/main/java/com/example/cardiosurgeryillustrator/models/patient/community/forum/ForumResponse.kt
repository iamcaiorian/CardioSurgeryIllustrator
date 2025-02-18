package com.example.cardiosurgeryillustrator.models.patient.community.forum

import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentResponse
import java.time.LocalDateTime

data class ForumResponse(
    val id: String,
    val theme: String,
    val title: String,
    val comments: List<CommentResponse>,
    var likesAmount: Int,
    var commentsAmount: Int,
    val createdAt: String
)
