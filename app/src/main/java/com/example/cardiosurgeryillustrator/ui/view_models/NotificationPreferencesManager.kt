package com.example.cardiosurgeryillustrator.ui.view_models

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "notification_preferences")

class NotificationPreferencesManager(context: Context) {
    private val dataStore = context.dataStore

    private val RISK_NOTIFICATION_KEY = booleanPreferencesKey("risk_notifications")
    private val HABITS_NOTIFICATION_KEY = booleanPreferencesKey("habits_notifications")

    val riskNotificationsFlow: Flow<Boolean>
        get() = dataStore.data.map { preferences ->
            preferences[RISK_NOTIFICATION_KEY] ?: false
        }

    val habitsNotificationsFlow: Flow<Boolean>
        get() = dataStore.data.map { preferences ->
            preferences[HABITS_NOTIFICATION_KEY] ?: false
        }

    suspend fun setRiskNotificationEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[RISK_NOTIFICATION_KEY] = enabled
        }
    }

    suspend fun setHabitsNotificationEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[HABITS_NOTIFICATION_KEY] = enabled
        }
    }
}

