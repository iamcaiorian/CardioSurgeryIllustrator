package com.example.cardiosurgeryillustrator.models.patient.home

data class Artery(
    val id: String,
    val arteryName: String,
    val imageRes: Int,
    val texts: List<InfoText>
)
