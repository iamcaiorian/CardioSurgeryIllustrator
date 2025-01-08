package com.example.cardiosurgeryillustrator.models

data class Module(
    val id: String,
    val subjectId: String,
    val title: String,
    val description: String,
    val cover: String,
    val progress: Float,
    val longDescription: String
)
