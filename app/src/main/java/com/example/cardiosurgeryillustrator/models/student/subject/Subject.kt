package com.example.cardiosurgeryillustrator.models.student.subject

import com.example.cardiosurgeryillustrator.models.student.module.Module

data class Subject(
    val id: String,
    val title: String,
    val description: String,
    val modules: List<Module> = emptyList()
)
