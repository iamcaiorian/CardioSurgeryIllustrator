package com.example.cardiosurgeryillustrator.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.cardiosurgeryillustrator.models.patient.appointment_schedule.Appointment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val Context.dataStore by preferencesDataStore(name = "user_prefs")

object DataStoreUtils {
    private val THEME_KEY = booleanPreferencesKey("is_dark_theme")
    private val APPOINTMENT_KEY = stringPreferencesKey("appointment")

    suspend fun saveTheme(context: Context, isDark: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = isDark
        }
    }

    fun readTheme(context: Context): Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[THEME_KEY] ?: false
    }

    suspend fun saveAppointments(context: Context, appointments: List<Appointment>) {
        val jsonString = Json.encodeToString(appointments)
        context.dataStore.edit { prefs ->
            prefs[APPOINTMENT_KEY] = jsonString
        }
    }

    fun readAppointments(context: Context): Flow<List<Appointment>> = context.dataStore.data.map { prefs ->
        prefs[APPOINTMENT_KEY]?.let { jsonString ->
            Json.decodeFromString<List<Appointment>>(jsonString)
        } ?: emptyList()
    }

    suspend fun deleteAppointment(context: Context, appointmentId: String) {
        val currentAppointments = readAppointments(context).first() // Coleta o fluxo de appointments
        val updatedAppointments = currentAppointments.filter { it.id != appointmentId } // Remove o agendamento

        // Salva a lista de agendamentos atualizada no DataStore
        saveAppointments(context, updatedAppointments)
    }
}

