package com.example.cardiosurgeryillustrator.view_models.student.subject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.student.subject.SubjectRepository

class SubjectViewModelFactory(private val subjectRepository: SubjectRepository) :
    ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubjectViewModel::class.java)) {
            return SubjectViewModel(subjectRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

