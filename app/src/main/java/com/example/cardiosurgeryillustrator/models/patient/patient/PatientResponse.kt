package com.example.cardiosurgeryillustrator.models.patient.patient

import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentResponse
import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumResponse
import java.util.UUID

data class PatientResponse(
    val userId: UUID,
    val questionsAndAnswers: String, // JSON String
    val createdForums: List<ForumResponse>,
    val likedForums: List<ForumResponse>,
    val savedForums: List<ForumResponse>,
    val comments: List<CommentResponse>
)
