package com.example.cardiosurgeryillustrator.ui.screens.notification

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.ui.view_models.NotificationPreferencesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NotificationViewModel(context: Context) : ViewModel() {
    private val preferencesManager = NotificationPreferencesManager(context)

    val riskNotificationEnabled: Flow<Boolean>
        get() = preferencesManager.riskNotificationsFlow

    val habitsNotificationEnabled: Flow<Boolean>
        get() = preferencesManager.habitsNotificationsFlow

    fun toggleRiskNotifications(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.setRiskNotificationEnabled(enabled)
        }
    }

    fun toggleHabitsNotifications(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.setHabitsNotificationEnabled(enabled)
        }
    }
}

