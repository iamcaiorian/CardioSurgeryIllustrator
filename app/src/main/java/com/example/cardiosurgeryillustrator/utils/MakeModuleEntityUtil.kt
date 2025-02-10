package com.example.cardiosurgeryillustrator.utils

import androidx.compose.runtime.mutableStateOf
import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.student.module.ModuleResponse

fun makeModuleEntityUtil(moduleResponse: ModuleResponse): Module {
        return Module(
            id = moduleResponse.id,
            subjectId = moduleResponse.subjectId,
            title = moduleResponse.title,
            description = moduleResponse.description,
            cover = moduleResponse.cover,
            progress = moduleResponse.progress,
            longDescription = moduleResponse.longDescription,
            isFavorite = mutableStateOf(moduleResponse.isFavorite),
            quiz = moduleResponse.quiz
        )
    
}