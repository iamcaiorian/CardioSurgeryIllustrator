package com.example.cardiosurgeryillustrator.view_models.student.password_recovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.password_recovery.PasswordRecoveryRequest
import com.example.cardiosurgeryillustrator.models.student.password_recovery.PasswordRecoveryResponse
import com.example.cardiosurgeryillustrator.repository.student.password_recovery.PasswordRecoveryRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PasswordRecoveryViewModel(private val repository: PasswordRecoveryRepository) : ViewModel() {

    var validCodeResponse: String? = null
        private set

    var newPasswordResponse: String? = null
        private set

    var generateTokenResponse: String? = null
        private set

    fun validCode(request: PasswordRecoveryRequest, onResult: (Response<PasswordRecoveryResponse>) -> Unit) {
        viewModelScope.launch {
            val response = repository.validCode(request)
            validCodeResponse = response.body()?.status
            onResult(response)
        }
    }

    fun changePassword(request: PasswordRecoveryRequest, onResult: (Response<PasswordRecoveryResponse>) -> Unit) {
        viewModelScope.launch {
            val response = repository.newPassword(request)
            newPasswordResponse = response.body()?.status
            onResult(response)
        }
    }

    fun generateToken(request: PasswordRecoveryRequest, onResult: (Response<PasswordRecoveryResponse>) -> Unit) {
        viewModelScope.launch {
            val response = repository.generateToken(request)
            generateTokenResponse = response.body()?.status
            onResult(response)
        }
    }
}
