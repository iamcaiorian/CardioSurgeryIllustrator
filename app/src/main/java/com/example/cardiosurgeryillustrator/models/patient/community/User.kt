package com.example.cardiosurgeryillustrator.models.patient.community;

data class User(
        val id: String,
        val avatarUrl: String,
        val savedTopics: List<String> = emptyList()
)