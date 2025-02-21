package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.student.module.ModuleCreateRequest
import com.example.cardiosurgeryillustrator.models.student.module.ModuleResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ModuleService {
    @POST("/module/")
    suspend fun createModule(@Body moduleCreateRequest: ModuleCreateRequest): ModuleResponse

    @PATCH("/module/{module_id}/toggle-favorite")
    suspend fun toggleFavorite(@Path("module_id") moduleId: String): ModuleResponse

    @GET("/module/")
    suspend fun getAllModules(): List<ModuleResponse>

    @GET("/module/by-subject/{subject_id}")
    suspend fun getAllModulesBySubjectId(@Path("subject_id") subjectId: String): List<ModuleResponse>

    @GET("/module/{module_id}")
    suspend fun getModuleById(@Path("module_id") moduleId: String): ModuleResponse
}