package com.example.cardiosurgeryillustrator.models.patient.nearby_clinics

data class Clinic (
    val id: String,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val phone: String
)