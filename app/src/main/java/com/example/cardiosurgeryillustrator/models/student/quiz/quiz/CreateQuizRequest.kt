package com.example.cardiosurgeryillustrator.models.student.quiz.quiz

data class CreateQuizRequest (
    val title: String,
    val description: String,
    val moduleId: String,
    val questionId: String,
)