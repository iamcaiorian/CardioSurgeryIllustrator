package com.example.cardiosurgeryillustrator.models.student.module

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.Quiz

data class Module(
    val id: String,
    val subjectId: String,
    val title: String,
    val description: String,
    val cover: String? = null,
    val progress: Float,
    val longDescription: String,
    var isFavorite: MutableState<Boolean> = mutableStateOf(false),
    val quiz: Quiz? = null
)
