package com.example.cardiosurgeryillustrator.view_models.student.subject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.student.subject.Subject
import com.example.cardiosurgeryillustrator.repository.student.subject.SubjectRepository
import kotlinx.coroutines.launch

class SubjectViewModel(
    private val subjectRepository: SubjectRepository
) : ViewModel() {

    var subjects: List<Subject> by mutableStateOf(listOf())

    fun getAllSubjects() {
        viewModelScope.launch {
            try {
                subjects = subjectRepository.getAllSubjects()
                println("Modules loaded: $subjects")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}