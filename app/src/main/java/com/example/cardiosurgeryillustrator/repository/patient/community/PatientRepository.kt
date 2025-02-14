package com.example.cardiosurgeryillustrator.repository.patient.community

import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance

class PatientRepository {
    suspend fun getAllForumsLiked(): List<String> {
        return RetrofitInstance.patientService.getAllLikedForumsId()
    }

    suspend fun getAllForumsSaved(): List<String> {
        return RetrofitInstance.patientService.getAllSavedForumsId()
    }
}