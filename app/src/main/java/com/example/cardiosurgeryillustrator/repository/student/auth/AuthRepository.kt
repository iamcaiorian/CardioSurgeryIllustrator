package com.example.cardiosurgeryillustrator.repository.student.auth

import com.example.cardiosurgeryillustrator.models.student.auth.AuthUserRequest
import com.example.cardiosurgeryillustrator.models.student.auth.AuthUserResponse
import com.example.cardiosurgeryillustrator.models.student.auth.CreateStudentRequest
import com.example.cardiosurgeryillustrator.models.student.auth.StudentResponse
import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance

class AuthRepository {

    suspend fun registerUser(createStudentRequest: CreateStudentRequest): StudentResponse {
        return RetrofitInstance.authService.registerUser(createStudentRequest)
    }

    suspend fun authUser(authUserRequest: AuthUserRequest): AuthUserResponse {
        return RetrofitInstance.authService.authUser(authUserRequest)
    }
}
