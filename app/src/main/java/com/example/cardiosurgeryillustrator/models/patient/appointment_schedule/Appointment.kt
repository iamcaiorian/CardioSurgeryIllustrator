package com.example.cardiosurgeryillustrator.models.patient.appointment_schedule

import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class Appointment(
    val id: String = UUID.randomUUID().toString(),
    val date: String,
    val time: String,
    val clinicId: String
)