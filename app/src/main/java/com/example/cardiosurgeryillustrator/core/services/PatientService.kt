package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumRequest
import com.example.cardiosurgeryillustrator.models.patient.patient.PatientRequest
import com.example.cardiosurgeryillustrator.models.patient.patient.PatientResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

import retrofit2.http.*

interface PatientService {

    @POST("/patient/create")
    suspend fun createPatient(@Body request: PatientRequest): PatientResponse

    @GET("/patient/{userId}")
    suspend fun getPatientById(@Path("userId") userId: String): PatientResponse

    @GET("/patient/form/{userId}")
    suspend fun getPatientForm(@Path("userId") userId: String): List<Map<String, Any>>

    // Obter todos os IDs dos fóruns curtidos por um paciente específic
    @GET("/patient/{patientId}/forum/liked")
    suspend fun getAllLikedForumsId(@Path("patientId") patientId: String): List<String>

    // Obter todos os IDs dos fóruns salvos por um paciente específico
    @GET("/patient/{patientId}/forum/saved")
    suspend fun getAllSavedForumsId(@Path("patientId") patientId: String): List<String>
}

