package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.student.subject.Subject
import retrofit2.http.GET

interface SubjectService {
    @GET("subject/")
    suspend fun getAllSubjects(): List<Subject>
}