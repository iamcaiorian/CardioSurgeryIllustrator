package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.student.module.ModuleCreateRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ModuleService {
    @POST("/module/")
    suspend fun createModule(@Body moduleCreateRequest: ModuleCreateRequest): Module

    @GET("/module/")
    suspend fun getAllModules(): List<Module>
}