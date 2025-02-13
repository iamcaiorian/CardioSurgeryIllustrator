package com.example.cardiosurgeryillustrator.models.student.auth

data class CreateStudentRequest(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val role: Role,
)

enum class Role {
    ADMIN,
    USER
}
