package com.example.cardiosurgeryillustrator.models.patient.appointment_schedule

import java.util.UUID


data class Appointment(
    val id: String = UUID.randomUUID().toString(),
    val date: String,
    val time: String,
    val clinicId: String
)