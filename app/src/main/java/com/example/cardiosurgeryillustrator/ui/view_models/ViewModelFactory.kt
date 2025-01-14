package com.example.cardiosurgeryillustrator.ui.view_models

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.ui.screens.notification.NotificationViewModel

class NotificationViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotificationViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
