package com.example.cardiosurgeryillustrator.models.patient.home

import androidx.compose.ui.unit.Dp

data class HeartDisease(
    val name: String,
    val points: List<Point>
)

data class Point(
    val xOffset: Dp,
    val yOffset: Dp,
    val route: String
)
