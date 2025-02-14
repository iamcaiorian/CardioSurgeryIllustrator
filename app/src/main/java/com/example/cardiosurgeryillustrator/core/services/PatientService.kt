package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.patient.community.topic.TopicRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PatientService {
    @GET("/patient/forum/liked")
    suspend fun getAllLikedForumsId(): List<String>

    @GET("/patient/forum/saved")
    suspend fun getAllSavedForumsId(): List<String>

    @POST("/patient/forum/save")
    suspend fun saveForum(@Body request: TopicRequest)

    @POST("/patient/forum/like")
    suspend fun likeForum(@Body request: TopicRequest)
}