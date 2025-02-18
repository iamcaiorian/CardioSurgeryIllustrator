package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.student.password_recovery.PasswordRecoveryRequest
import com.example.cardiosurgeryillustrator.models.student.password_recovery.PasswordRecoveryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PasswordRecoveryService {

    @POST("/password-recovery/valid-code")
    suspend fun validCode(@Body passwordRecoveryRequest: PasswordRecoveryRequest): Response<PasswordRecoveryResponse>

    @POST("/password-recovery/new-password")
    suspend fun newPassword(@Body passwordRecoveryRequest: PasswordRecoveryRequest): Response<PasswordRecoveryResponse>

    @POST("/password-recovery/generate-code")
    suspend fun generateToken(@Body passwordRecoveryRequest: PasswordRecoveryRequest): Response<PasswordRecoveryResponse>
}