package com.example.cardiosurgeryillustrator.ui.view_models.admin.create_module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.module.ModuleCreateRequest
import com.example.cardiosurgeryillustrator.repository.module.ModuleRepository
import kotlinx.coroutines.launch

class CreateModuleViewModel(private val repository: ModuleRepository): ViewModel() {

    fun createModule(moduleCreateRequest: ModuleCreateRequest) {
        viewModelScope.launch {
            repository.createModule(moduleCreateRequest)
        }
    }
}