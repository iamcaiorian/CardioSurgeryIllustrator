package com.example.cardiosurgeryillustrator.repository.subject

import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.student.subject.Subject

class SubjectRepository {
    suspend fun getAllSubjects(): List<Subject> {
        return RetrofitInstance.subjectService.getAllSubjects()
    }
}