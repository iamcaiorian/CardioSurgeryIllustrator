package com.example.cardiosurgeryillustrator.models.patient.nearby_clinics

data class OverpassResponse(
    val elements: List<Element>
)

data class Element(
    val id: Long,
    val lat: Double,
    val lon: Double,
    val tags: Map<String, String>
)
