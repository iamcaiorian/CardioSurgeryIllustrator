package com.example.cardiosurgeryillustrator.models

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>? = null,
    val type: QuestionType
)

enum class QuestionType {
    CHECKBOX, RADIOBUTTON, TEXTINPUT
}
