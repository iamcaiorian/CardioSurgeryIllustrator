package com.example.cardiosurgeryillustrator.repository.patient.community



import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.patient.patient.PatientRequest
import com.example.cardiosurgeryillustrator.models.patient.patient.PatientResponse

class PatientRepository() {

    // Criar um novo paciente
    suspend fun createPatient(patientRequest: PatientRequest): PatientResponse {
        return RetrofitInstance.patientService.createPatient(patientRequest)
    }

    // Obter informações de um paciente pelo ID
    suspend fun getPatientById(userId: String): PatientResponse {
        return RetrofitInstance.patientService.getPatientById(userId)
    }

    // Obter o formulário de perguntas e respostas de um paciente pelo ID
    suspend fun getPatientForm(userId: String): List<Map<String, Any>> {
        return RetrofitInstance.patientService.getPatientForm(userId)
    }

    // Obter todos os IDs dos fóruns curtidos por um paciente específico
    suspend fun getAllForumsLiked(patientId: String): List<String> {
        return RetrofitInstance.patientService.getAllLikedForumsId(patientId)
    }

    // Obter todos os IDs dos fóruns salvos por um paciente específico
    suspend fun getAllForumsSaved(patientId: String): List<String> {
        return RetrofitInstance.patientService.getAllSavedForumsId(patientId)
    }
}
