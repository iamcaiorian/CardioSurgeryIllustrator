package com.example.cardiosurgeryillustrator.view_models.student.password_recovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.student.password_recovery.PasswordRecoveryRepository

class PasswordRecoveryViewModelFactory(private val repository: PasswordRecoveryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PasswordRecoveryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PasswordRecoveryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
