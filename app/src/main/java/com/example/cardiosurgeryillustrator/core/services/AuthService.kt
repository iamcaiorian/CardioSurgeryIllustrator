package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.student.auth.AuthUserRequest
import com.example.cardiosurgeryillustrator.models.student.auth.AuthUserResponse
import com.example.cardiosurgeryillustrator.models.student.auth.CreateStudentRequest
import com.example.cardiosurgeryillustrator.models.student.auth.StudentResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/register-user")
    suspend fun registerUser(@Body createStudentRequest: CreateStudentRequest): StudentResponse

    @POST("/auth/login")
    suspend fun authUser(@Body authUserRequest: AuthUserRequest): AuthUserResponse
}