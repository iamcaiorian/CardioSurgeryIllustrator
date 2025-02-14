package com.example.cardiosurgeryillustrator.models.student.password_recovery

import android.provider.ContactsContract.CommonDataKinds.Email

data class PasswordRecoveryRequest(
    val email: String,
    val code: String? = null,
    val password: String? = null
)

