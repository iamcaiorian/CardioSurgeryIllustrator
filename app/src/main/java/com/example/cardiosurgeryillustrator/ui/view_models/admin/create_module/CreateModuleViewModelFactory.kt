package com.example.cardiosurgeryillustrator.ui.view_models.admin.create_module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.module.ModuleRepository
import com.example.cardiosurgeryillustrator.repository.subject.SubjectRepository

class CreateModuleViewModelFactory(
    private val moduleRepository: ModuleRepository,
    private val subjectRepository: SubjectRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateModuleViewModel::class.java)) {
            return CreateModuleViewModel(moduleRepository, subjectRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
