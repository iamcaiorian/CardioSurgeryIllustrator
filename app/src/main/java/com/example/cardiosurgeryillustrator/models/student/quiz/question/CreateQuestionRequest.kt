package com.example.cardiosurgeryillustrator.models.student.quiz.question

data class CreateQuestionRequest(
    val problem: String,
    val alternativeA: String,
    val alternativeB: String,
    val alternativeC: String,
    val alternativeD: String,
    val answer: String,
)
