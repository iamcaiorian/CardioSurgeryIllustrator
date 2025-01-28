package com.example.cardiosurgeryillustrator.view_models.admin.admin_modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.student.subject.SubjectRepository

class AdminModulesViewModelFactory(
    private val subjectRepository: SubjectRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminModulesViewModel::class.java)) {
            return AdminModulesViewModel( subjectRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
