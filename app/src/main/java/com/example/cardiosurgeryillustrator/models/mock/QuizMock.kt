package com.example.cardiosurgeryillustrator.models.mock

import com.example.cardiosurgeryillustrator.models.Quiz

val mockQuizzes = listOf(
    Quiz(
        id = "1",
        title = "Quiz 1",
        subtitle = "Assunto 1",
        question = "Dado os tipos de corações existentes, qual é adulto?",
        description = "Dado os tipos de coração existentes, apenas um é adulto e todos possuem obstruções cardíacas.",
        correctAnswer = "Certo"
    ),
    Quiz(
        id = "2",
        title = "Quiz 2",
        subtitle = "Assunto 2",
        question = "Qual a principal função da artéria coronária direita?",
        description = "",
        options = listOf(
            "Transportar oxigênio para o coração",
            "Levar sangue arterial para o miocárdio",
            "Retirar dióxido de carbono do coração",
            "Controlar os batimentos cardíacos"
        ),
        correctAnswer = "Levar sangue arterial para o miocárdio"
    )
)
