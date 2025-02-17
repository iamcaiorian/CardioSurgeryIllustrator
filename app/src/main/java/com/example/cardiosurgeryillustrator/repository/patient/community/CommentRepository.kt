package com.example.cardiosurgeryillustrator.repository.patient.community


import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentRequest
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentResponse

class CommentRepository() {

    suspend fun createComment(commentRequest: CommentRequest): CommentResponse {
        return RetrofitInstance.commentService.createComment(commentRequest)
    }

    suspend fun getAllComments(): List<CommentResponse> {
        return RetrofitInstance.commentService.getAllComments()
    }

    suspend fun getCommentById(commentId: String): CommentResponse {
        return RetrofitInstance.commentService.getCommentById(commentId)
    }
}
