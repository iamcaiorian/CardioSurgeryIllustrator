package com.example.cardiosurgeryillustrator.models.student.subject

import com.example.cardiosurgeryillustrator.models.student.module.ModuleResponse

data class Subject(
    val id: String,
    val title: String,
    val description: String,
    val modules: List<ModuleResponse> = emptyList()
)
