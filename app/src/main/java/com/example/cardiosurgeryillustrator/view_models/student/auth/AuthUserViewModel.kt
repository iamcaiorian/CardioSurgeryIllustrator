package com.example.cardiosurgeryillustrator.view_models.student.auth

import com.example.cardiosurgeryillustrator.repository.student.auth.AuthRepository


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.auth.AuthUserRequest
import com.example.cardiosurgeryillustrator.models.student.auth.AuthUserResponse
import com.example.cardiosurgeryillustrator.models.student.auth.CreateStudentRequest
import com.example.cardiosurgeryillustrator.models.student.auth.StudentResponse
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val context: Context
) : ViewModel() {

    fun registerUser(createStudentRequest: CreateStudentRequest, onResult: (StudentResponse?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = authRepository.registerUser(createStudentRequest)
                onResult(response)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(null)
            }
        }
    }

    fun authUser(authUserRequest: AuthUserRequest, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response: AuthUserResponse = authRepository.authUser(authUserRequest)

                DataStoreUtils.saveToken(context, response.token)

                onResult(true)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }
    }
}
