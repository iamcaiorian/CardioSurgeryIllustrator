package com.example.cardiosurgeryillustrator.view_models.student.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository

class ModulesViewModelFactory(private val moduleRepository: ModuleRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ModulesViewModel::class.java)) {
            return ModulesViewModel(moduleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

