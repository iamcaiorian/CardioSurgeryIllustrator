package com.example.cardiosurgeryillustrator.models

data class Artery(
    val id: String,
    val arteryName: String,
    val imageRes: Int,
    val texts: List<InfoText>
)
