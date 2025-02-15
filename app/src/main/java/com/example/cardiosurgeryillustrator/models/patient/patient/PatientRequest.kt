package com.example.cardiosurgeryillustrator.models.patient.patient

import java.util.UUID

data class PatientRequest(
    val userId: UUID,
    val questionsAndAnswers: List<QuestionAndAnswer>
)

data class QuestionAndAnswer(
    val question: String,
    val answer: Any
)


