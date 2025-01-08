package com.example.cardiosurgeryillustrator.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

object DataStoreUtils {
    private val THEME_KEY = booleanPreferencesKey("is_dark_theme")

    suspend fun saveTheme(context: Context, isDark: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = isDark
        }
    }

    fun readTheme(context: Context): Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[THEME_KEY] ?: false
    }
}