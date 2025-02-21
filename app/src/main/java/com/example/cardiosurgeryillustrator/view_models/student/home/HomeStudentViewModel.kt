package com.example.cardiosurgeryillustrator.view_models.student.home

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.module.ModuleResponse
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeStudentViewModel (
    private val moduleRepository: ModuleRepository,
    private val context: Context
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

    fun loadLastModuleOpenedId(onResult: (String?) -> Unit) {
        viewModelScope.launch {
            try {
                val lastModuleId = DataStoreUtils.redLastModuleOpenedId(context).first()
                onResult(lastModuleId)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(null)
            }
        }
    }
}