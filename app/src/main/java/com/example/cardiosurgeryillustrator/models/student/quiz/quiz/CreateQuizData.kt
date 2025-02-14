package com.example.cardiosurgeryillustrator.models.student.quiz.quiz

data class CreateQuizData (
    val title: String,
    val description: String,
    val questionEntityList: List<CreateQuizQuestionRequest>,
)

data class CreateQuizQuestionRequest(
    val problem: String,
    val alternativeA: String,
    val alternativeB: String,
    val alternativeC: String,
    val alternativeD: String,
    val answer: String
)