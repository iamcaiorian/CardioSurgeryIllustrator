package com.example.cardiosurgeryillustrator.view_models.student.study

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.module.ModuleResponse
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudyViewModel(
    private val moduleRepository: ModuleRepository,
    private val context: Context
) : ViewModel() {

    private val _module = MutableStateFlow<ModuleResponse?>(null)
    val module = _module.asStateFlow()

    fun getModuleById(moduleId: String) {
        viewModelScope.launch {
            try {
                _module.update { moduleRepository.getModuleById(moduleId) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun saveLastModuleOpenedId(moduleId: String) {
        viewModelScope.launch {
            try {
                DataStoreUtils.saveLastModuleOpenedId(context, moduleId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
