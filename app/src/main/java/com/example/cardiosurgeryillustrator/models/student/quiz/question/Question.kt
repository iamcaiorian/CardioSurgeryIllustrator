package com.example.cardiosurgeryillustrator.models.student.quiz.question

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>? = null,
    val type: QuestionType
)


