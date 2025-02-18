package com.example.cardiosurgeryillustrator.view_models.student.study

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository

class StudyViewModelFactory(
    private val moduleRepository: ModuleRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudyViewModel(moduleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}