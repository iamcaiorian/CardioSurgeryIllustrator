package com.example.cardiosurgeryillustrator.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Module(
    val id: String,
    val subjectId: String,
    val title: String,
    val description: String,
    val cover: String,
    val progress: Float,
    val longDescription: String,
    val isFavorite: MutableState<Boolean> = mutableStateOf(false)
)
