package com.example.cardiosurgeryillustrator.utils.notification

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.cardiosurgeryillustrator.utils.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotificationPreferencesManager(private val context: Context) {

    private val RISK_NOTIFICATION_KEY = booleanPreferencesKey("risk_notifications")
    private val HABITS_NOTIFICATION_KEY = booleanPreferencesKey("habits_notifications")

    val riskNotificationsFlow: Flow<Boolean>
        get() = context.dataStore.data.map { preferences ->
            preferences[RISK_NOTIFICATION_KEY] ?: false
        }

    val habitsNotificationsFlow: Flow<Boolean>
        get() = context.dataStore.data.map { preferences ->
            preferences[HABITS_NOTIFICATION_KEY] ?: false
        }

    suspend fun setRiskNotificationEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[RISK_NOTIFICATION_KEY] = enabled
        }
    }

    suspend fun setHabitsNotificationEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[HABITS_NOTIFICATION_KEY] = enabled
        }
    }
}
