package com.example.cardiosurgeryillustrator.models.patient.home

data class LifeStyleText(
    val id: Int,
    val imc: Float,
    val explanation: String,
    val food: List<String>,
    val lifeStyle: List<String>
)