package com.example.cardiosurgeryillustrator.view_models.patient.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.patient.community.CommentRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository

class ForumViewModelFactory(
    private val forumRepository: ForumRepository,
    private val commentRepository: CommentRepository,
    private val patientRepository: PatientRepository
) : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForumViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ForumViewModel(forumRepository, commentRepository, patientRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}