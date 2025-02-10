package com.example.cardiosurgeryillustrator.models.student.quiz

data class Quiz(
    val id: String? = null,
    val title: String,
    val description: String,
    val questionEntityList: List<QuizQuestion> = emptyList()
)

data class QuizQuestion(
    val id: String,
    val problem: String,
    val alternativeA: String,
    val alternativeB: String,
    val alternativeC: String,
    val alternativeD: String,
    val answer: String
)