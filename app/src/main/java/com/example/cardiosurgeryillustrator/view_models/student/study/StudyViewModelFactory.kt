package com.example.cardiosurgeryillustrator.view_models.student.study

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository

class StudyViewModelFactory(
    private val moduleRepository: ModuleRepository,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudyViewModel(moduleRepository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}