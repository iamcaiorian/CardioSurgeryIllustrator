package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentRequest
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentResponse
import retrofit2.http.*

interface CommentService {

    @POST("/comment/")
    suspend fun createComment(@Body commentRequest: CommentRequest): CommentResponse

    @GET("/comment/")
    suspend fun getAllComments(): List<CommentResponse>

    @GET("/comment/{commentId}")
    suspend fun getCommentById(@Path("commentId") commentId: String): CommentResponse
}
