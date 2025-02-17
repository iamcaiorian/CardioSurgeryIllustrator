package com.example.cardiosurgeryillustrator.view_models.patient.community

import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository

class CommunityViewModelFactory(
    private val forumRepository: ForumRepository,
    private val patientRepository: PatientRepository
) : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommunityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CommunityViewModel(forumRepository, patientRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}