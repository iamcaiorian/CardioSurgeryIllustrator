package com.example.cardiosurgeryillustrator.models.student.quiz

import java.util.UUID

data class CreateQuizRequest (
    val title: String,
    val description: String,
    val questionEntityList: List<CreateQuizQuestionRequest>,
)

data class CreateQuizQuestionRequest(
    val id: String= UUID.randomUUID().toString(),
    val problem: String,
    val alternativeA: String,
    val alternativeB: String,
    val alternativeC: String,
    val alternativeD: String,
    val answer: String
)