package com.example.cardiosurgeryillustrator.models.student.auth

import java.util.*

data class Authority(
    val authority: String
)

data class StudentResponse(
    val id: UUID,
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val role: Role,
    val enabled: Boolean,
    val authorities: List<Authority>,
    val username: String,
    val credentialsNonExpired: Boolean,
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean
)
