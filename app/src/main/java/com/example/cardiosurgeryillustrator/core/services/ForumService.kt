package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.patient.community.Message
import com.example.cardiosurgeryillustrator.models.patient.community.topic.TopicRequest
import com.example.cardiosurgeryillustrator.models.patient.community.topic.TopicResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ForumService {
    @POST("/forum/")
    suspend fun createForum(@Body topicRequest: TopicRequest): TopicResponse

    @GET("/forum/")
    suspend fun getAllForums(): List<TopicResponse>

    @POST("/forum/{forum_id}/messages")
    suspend fun postNewMessage(
        @Path("forum_id") forumId: String,
        @Body message: Message
    )

    @DELETE("/forum/{forum_id}")
    suspend fun deleteForumById(@Path("forum_id") forumId: String)

    @GET("/forum/{forum_id}/messages")
    suspend fun getAllMessagesForForum(@Path("forum_id") forumId: String): List<Message>
}