package com.example.cardiosurgeryillustrator.view_models.patient.community

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository
import com.example.cardiosurgeryillustrator.view_models.patient.community.CommunityViewModel

class CommunityViewModelFactory(
    private val forumRepository: ForumRepository,
    private val patientRepository: PatientRepository,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommunityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CommunityViewModel(forumRepository, patientRepository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
