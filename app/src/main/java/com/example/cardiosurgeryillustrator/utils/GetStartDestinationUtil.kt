package com.example.cardiosurgeryillustrator.utils

import com.example.cardiosurgeryillustrator.navigation.AppFlow

fun getStartDestination(navigateTo: String?, habitTitle: String?, habitDescription: String?, isAuthenticated: Boolean): String {
    return when (navigateTo) {
        "habit_detail" -> "habit_detail/${habitTitle ?: "Detalhes"}/${habitDescription ?: "Aqui você sempre manterá bons hábitos!"}"
        else -> if (isAuthenticated) AppFlow.StudentFlow.route else AppFlow.WelcomeFlow.route
    }
}