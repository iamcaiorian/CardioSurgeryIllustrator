package com.example.cardiosurgeryillustrator.models.mock.student

import androidx.compose.runtime.mutableStateOf
import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz
import com.example.cardiosurgeryillustrator.models.student.quiz.QuizQuestion

val mockModules = listOf(
    Module(
        id = "1",
        subjectId = "4",
        title = "Anatomia do Coração",
        description = "Exploração detalhada das câmaras, válvulas, vasos sanguíneos e nervos que compõem o coração humano.",
        cover = "https://example.com/covers/anatomia_coracao.jpg",
        progress = 0.75f,
        longDescription = """
            O coração humano é um órgão vital composto por quatro câmaras: átrios esquerdo e direito, e ventrículos esquerdo e direito...
        """.trimIndent(),
        isFavorite = mutableStateOf(false),
        quiz = Quiz(
            id = "101",
            title = "Quiz de Anatomia Cardíaca",
            description = "Teste seus conhecimentos sobre a anatomia do coração humano.",
            questionEntityList = listOf(
                QuizQuestion(
                    id = "q1",
                    problem = "Qual câmara do coração bombeia sangue para os pulmões?",
                    alternativeA = "Átrio esquerdo",
                    alternativeB = "Átrio direito",
                    alternativeC = "Ventrículo esquerdo",
                    alternativeD = "Ventrículo direito",
                    answer = "D"
                )
            )
        )
    ),
    Module(
        id = "2",
        subjectId = "5",
        title = "Eletrocardiograma (ECG)",
        description = "Estudo dos princípios básicos do ECG, incluindo interpretação de ondas, intervalos e ritmos cardíacos.",
        cover = "https://example.com/covers/eletrocardiograma.jpg",
        progress = 0.45f,
        longDescription = """
            O eletrocardiograma (ECG) é uma ferramenta diagnóstica essencial que registra a atividade elétrica do coração...
        """.trimIndent(),
        isFavorite = mutableStateOf(false),
        quiz = Quiz(
            id = "102",
            title = "Quiz de ECG",
            description = "Teste seus conhecimentos sobre interpretação de eletrocardiogramas.",
            questionEntityList = listOf(
                QuizQuestion(
                    id = "q2",
                    problem = "Qual onda do ECG representa a despolarização ventricular?",
                    alternativeA = "Onda P",
                    alternativeB = "Complexo QRS",
                    alternativeC = "Onda T",
                    alternativeD = "Onda U",
                    answer = "B"
                )
            )
        )
    ),
    Module(
        id = "3",
        subjectId = "1",
        title = "Insuficiência Cardíaca",
        description = "Abordagem dos tipos de insuficiência cardíaca, diagnóstico e opções terapêuticas disponíveis.",
        cover = "https://example.com/covers/insuficiencia_cardiaca.jpg",
        progress = 0.60f,
        longDescription = """
            A insuficiência cardíaca ocorre quando o coração é incapaz de bombear sangue suficiente para atender às necessidades metabólicas do corpo...
        """.trimIndent(),
        isFavorite = mutableStateOf(false),
        quiz = Quiz(
            id = "103",
            title = "Quiz de Insuficiência Cardíaca",
            description = "Avalie seu conhecimento sobre insuficiência cardíaca e tratamentos.",
            questionEntityList = listOf(
                QuizQuestion(
                    id = "q3",
                    problem = "Qual das seguintes opções NÃO é uma causa comum de insuficiência cardíaca?",
                    alternativeA = "Hipertensão arterial",
                    alternativeB = "Infarto do miocárdio",
                    alternativeC = "Diabetes tipo 1",
                    alternativeD = "Doenças valvares",
                    answer = "C"
                )
            )
        )
    )
)
