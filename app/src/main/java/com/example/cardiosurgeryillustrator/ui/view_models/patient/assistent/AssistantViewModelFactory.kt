package com.example.cardiosurgeryillustrator.ui.view_models.patient.assistent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.assistent.AssistentRepository

class AssistantViewModelFactory(
    private val assistentRepository: AssistentRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssistantViewModel::class.java)) {
            return AssistantViewModel(assistentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
