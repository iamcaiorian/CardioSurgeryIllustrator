package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumRequest
import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

import retrofit2.http.*

interface ForumService {

    // Criar um fórum
    @POST("/forum/")
    suspend fun createForum(@Body createForum: ForumRequest): ForumResponse

    // Obter todos os fóruns
    @GET("/forum/")
    suspend fun getAllForums(): List<ForumResponse>

    // Obter um fórum por ID
    @GET("/forum/{forumId}")
    suspend fun getForumById(@Path("forumId") forumId: String): ForumResponse

    @POST("/forum/{forumId}/like/{patientId}")
    suspend fun likeForum(
        @Path("forumId") forumId: String,
        @Path("patientId") patientId: String
    )

    @POST("/forum/{forumId}/save/{patientId}")
    suspend fun saveForum(
        @Path("forumId") forumId: String,
        @Path("patientId") patientId: String
    )

    @DELETE("/forum/{forumId}")
    suspend fun deleteForumById(@Path("forumId") forumId: String)
}
