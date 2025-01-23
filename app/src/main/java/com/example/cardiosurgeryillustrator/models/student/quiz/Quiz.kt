package com.example.cardiosurgeryillustrator.models.student.quiz

data class Quiz (
    val id: String,
    val title: String,
    val subtitle: String,
    val question: String,
    val description: String,
    val options: List<String>? = null,
    val correctAnswer: String? = null
)