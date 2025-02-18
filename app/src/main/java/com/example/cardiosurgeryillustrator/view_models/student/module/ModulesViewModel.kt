package com.example.cardiosurgeryillustrator.view_models.student.module

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.module.ModuleResponse
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import kotlinx.coroutines.launch

class ModulesViewModel(
    private val moduleRepository: ModuleRepository
) : ViewModel()  {
    var modules: List<ModuleResponse> by mutableStateOf(listOf())

    fun getAllModulesBySubjectId(subjectId: String) {
        viewModelScope.launch {
            try {
                modules = moduleRepository.getAllModulesBySubjectId(subjectId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}