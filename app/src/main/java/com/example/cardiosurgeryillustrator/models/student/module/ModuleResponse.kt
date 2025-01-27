package com.example.cardiosurgeryillustrator.models.student.module

data class ModuleResponse(
    val id: String,
    val subjectId: String,
    val title: String,
    val description: String,
    val cover: String,
    val progress: Float,
    val longDescription: String,
    var isFavorite: Boolean
)
