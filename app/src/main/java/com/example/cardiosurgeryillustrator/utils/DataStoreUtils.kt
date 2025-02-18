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

    private val PATIENT_UUID_KEY = stringPreferencesKey("patient_uuid")
    private val IMC_KEY = stringPreferencesKey("imc")
    private val QUESTION_14_KEY = stringPreferencesKey("question_14")
    private val USER_DISEASE_INDEX = stringPreferencesKey("user_disease_index")

    private val TOKEN_KEY = stringPreferencesKey("auth_token")
    private val TOKEN_ADMIN_KEY = stringPreferencesKey("auth_token_admin")


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

    fun readAppointments(context: Context): Flow<List<Appointment>> =
        context.dataStore.data.map { prefs ->
            prefs[APPOINTMENT_KEY]?.let { jsonString ->
                Json.decodeFromString<List<Appointment>>(jsonString)
            } ?: emptyList()
        }

    suspend fun deleteAppointment(context: Context, appointmentId: String) {
        val currentAppointments = readAppointments(context).first()
        val updatedAppointments = currentAppointments.filter { it.id != appointmentId }

        saveAppointments(context, updatedAppointments)
    }

    suspend fun savePatientUUID(context: Context, uuid: String) {
        context.dataStore.edit { preferences ->
            preferences[PATIENT_UUID_KEY] = uuid
        }
    }

    fun readPatientUUID(context: Context): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[PATIENT_UUID_KEY]
        }
    }

    suspend fun saveQuestion14Response(context: Context, response: String) {
        context.dataStore.edit { preferences ->
            preferences[QUESTION_14_KEY] = response
        }
    }

    suspend fun saveIMC(context: Context, imc: String) {
        context.dataStore.edit { preferences ->
            preferences[IMC_KEY] = imc
        }
    }

    fun readQuestion14Response(context: Context): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[QUESTION_14_KEY]
        }
    }

    fun readImc(context: Context): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[IMC_KEY]
        }
    }


    suspend fun saveToken(context: Context, token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    fun readToken(context: Context): Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[TOKEN_KEY]
    }

    suspend fun saveTokenAdmin(context: Context, token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_ADMIN_KEY] = token
        }
    }

    fun readTokenAdmin(context: Context): Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[TOKEN_ADMIN_KEY]
    }

    suspend fun clearToken(context: Context) {
        context.dataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
        }
    }

    suspend fun saveUserDiseaseIndex(context: Context, index: Int) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey("user_disease_index")] = index.toString()
        }
    }

    fun readUserDiseaseIndex(context: Context): Flow<Int?> {
        return context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey("user_disease_index")]?.toIntOrNull()
        }
    }
}

