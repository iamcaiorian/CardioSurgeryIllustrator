package com.example.cardiosurgeryillustrator.view_models.mainScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class MainScreenViewModel : ViewModel() {
    var isAuthenticated by mutableStateOf(false)
        private set

    fun login() {
        isAuthenticated = true
    }

    fun logout() {
        isAuthenticated = false
    }
}