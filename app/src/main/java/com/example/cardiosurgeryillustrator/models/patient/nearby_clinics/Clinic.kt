package com.example.cardiosurgeryillustrator.models.patient.nearby_clinics


data class Clinic(
    val id: String = "",
    val name: String,
    val description: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val address: String? = null,
    val phone: String? = null
)