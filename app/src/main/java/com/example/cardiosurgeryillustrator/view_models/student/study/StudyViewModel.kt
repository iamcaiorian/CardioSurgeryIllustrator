package com.example.cardiosurgeryillustrator.view_models.student.study

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.module.ModuleResponse
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import kotlinx.coroutines.launch

class StudyViewModel(
    private val moduleRepository: ModuleRepository
) : ViewModel() {

    var module: ModuleResponse? by mutableStateOf(null)

    fun getModuleById(moduleId: String) {
        viewModelScope.launch {
            try {
                module = moduleRepository.getModuleById(moduleId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
