package com.example.cardiosurgeryillustrator.models.patient.community;

data class Patient(
        val id: String,
        val avatarUrl: String,
        val likedTopicsId: List<String> = emptyList(),
        val savedTopicsId: List<String> = emptyList()
)