package com.example.cardiosurgeryillustrator.view_models.student.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.student.module.ModuleRepository

class HomeStudentViewModelFactory(
    private val moduleRepository: ModuleRepository,
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeStudentViewModel::class.java)) {
            return HomeStudentViewModel(moduleRepository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
