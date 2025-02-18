package com.example.cardiosurgeryillustrator.models.patient.community

import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentResponse
import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumResponse
import com.example.cardiosurgeryillustrator.models.patient.patient.QuestionAndAnswer
import java.util.UUID

data class Patient(
        val userId: UUID,
        val questionsAndAnswers: List<QuestionAndAnswer>,
        val createdForums: List<ForumResponse>,
        val likedForums: List<ForumResponse>,
        val savedForums: List<ForumResponse>,
        val comments: List<CommentResponse>
)