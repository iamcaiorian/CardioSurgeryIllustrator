package com.example.cardiosurgeryillustrator.view_models.patient.assistent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.patient.assistent.AssistantRepository

class AssistantViewModelFactory(
    private val assistantRepository: AssistantRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssistantViewModel::class.java)) {
            return AssistantViewModel(assistantRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
