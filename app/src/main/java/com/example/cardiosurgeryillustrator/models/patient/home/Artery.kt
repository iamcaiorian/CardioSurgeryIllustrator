package com.example.cardiosurgeryillustrator.models.patient.home

import com.example.cardiosurgeryillustrator.models.InfoText

data class Artery(
    val id: String,
    val arteryName: String,
    val imageRes: Int,
    val texts: List<InfoText>
)
