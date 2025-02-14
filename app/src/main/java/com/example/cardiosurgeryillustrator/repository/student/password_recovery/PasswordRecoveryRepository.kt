package com.example.cardiosurgeryillustrator.repository.student.password_recovery

import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.student.password_recovery.PasswordRecoveryRequest
import com.example.cardiosurgeryillustrator.models.student.password_recovery.PasswordRecoveryResponse
import retrofit2.Response

class PasswordRecoveryRepository() {

    suspend fun validCode(passwordRecoveryRequest: PasswordRecoveryRequest): Response<PasswordRecoveryResponse> {
        return RetrofitInstance.passwordRecoveryService.validCode(passwordRecoveryRequest)
    }

    suspend fun newPassword(passwordRecoveryRequest: PasswordRecoveryRequest): Response<PasswordRecoveryResponse>{
        return RetrofitInstance.passwordRecoveryService.newPassword(passwordRecoveryRequest)
    }

    suspend fun generateToken(passwordRecoveryRequest: PasswordRecoveryRequest): Response<PasswordRecoveryResponse> {
        return RetrofitInstance.passwordRecoveryService.generateToken(passwordRecoveryRequest)
    }
}
