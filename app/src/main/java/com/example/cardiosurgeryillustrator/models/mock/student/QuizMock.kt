package com.example.cardiosurgeryillustrator.models.mock.student

import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.Quiz
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.QuizQuestion

val mockQuizzes = listOf(
    Quiz(
        id = "1",
        title = "Quiz 1",
        description = "Detalhes sobre a anatomia básica do coração humano.",
        questionEntityList = listOf(
            QuizQuestion(
                id = "1",
                problem = "Quantas câmaras o coração humano possui?",
                alternativeA = "2",
                alternativeB = "3",
                alternativeC = "4",
                alternativeD = "5",
                answer = "4"
            )
        )
    ),
    Quiz(
        id = "2",
        title = "Quiz 2",
        description = "Explicação sobre o que o eletrocardiograma monitora.",
        questionEntityList = listOf(
            QuizQuestion(
                id = "2",
                problem = "O que o ECG mede?",
                alternativeA = "Pressão arterial",
                alternativeB = "Atividade elétrica do coração",
                alternativeC = "Quantidade de sangue bombeado",
                alternativeD = "Níveis de oxigênio no sangue",
                answer = "Atividade elétrica do coração"
            )
        )
    ),
    Quiz(
        id = "3",
        title = "Quiz 3",
        description = "Sobre os sinais de insuficiência cardíaca.",
        questionEntityList = listOf(
            QuizQuestion(
                id = "3",
                problem = "Qual é um dos sintomas mais comuns da insuficiência cardíaca?",
                alternativeA = "Falta de ar ao realizar esforços",
                alternativeB = "Dor intensa no peito",
                alternativeC = "Desmaios frequentes",
                alternativeD = "Palidez",
                answer = "Falta de ar ao realizar esforços"
            )
        )
    ),
    Quiz(
        id = "4",
        title = "Quiz 4",
        description = "Compreensão dos níveis de pressão arterial.",
        questionEntityList = listOf(
            QuizQuestion(
                id = "4",
                problem = "O que caracteriza a hipertensão arterial?",
                alternativeA = "Pressão arterial abaixo de 120/80",
                alternativeB = "Pressão arterial acima de 140/90",
                alternativeC = "Redução no fluxo sanguíneo coronário",
                alternativeD = "Pressão constante de 110/70",
                answer = "Pressão arterial acima de 140/90"
            )
        )
    ),
    Quiz(
        id = "5",
        title = "Quiz 5",
        description = "Causas comuns dessa condição cardíaca.",
        questionEntityList = listOf(
            QuizQuestion(
                id = "5",
                problem = "O que causa a doença arterial coronariana?",
                alternativeA = "Infecção viral no coração",
                alternativeB = "Acúmulo de placas de gordura nas artérias coronárias",
                alternativeC = "Ritmo cardíaco irregular",
                alternativeD = "Anemia severa",
                answer = "Acúmulo de placas de gordura nas artérias coronárias"
            )
        )
    ),
    Quiz(
        id = "6",
        title = "Quiz 6",
        description = "Sobre cirurgias cardiovasculares.",
        questionEntityList = listOf(
            QuizQuestion(
                id = "6",
                problem = "Qual é o objetivo principal de uma cirurgia de bypass coronário?",
                alternativeA = "Reduzir a pressão arterial",
                alternativeB = "Substituir válvulas danificadas",
                alternativeC = "Desviar o fluxo sanguíneo em torno de obstruções arteriais",
                alternativeD = "Monitorar o ritmo cardíaco",
                answer = "Desviar o fluxo sanguíneo em torno de obstruções arteriais"
            )
        )
    ),
    Quiz(
        id = "7",
        title = "Quiz 7",
        description = "Importância da reabilitação para pacientes cardíacos.",
        questionEntityList = listOf(
            QuizQuestion(
                id = "7",
                problem = "Qual é um dos principais benefícios da reabilitação cardiovascular?",
                alternativeA = "Aumento da pressão arterial",
                alternativeB = "Redução do colesterol LDL",
                alternativeC = "Melhora da capacidade física e qualidade de vida",
                alternativeD = "Substituição de válvulas cardíacas",
                answer = "Melhora da capacidade física e qualidade de vida"
            )
        )
    ),
    Quiz(
        id = "8",
        title = "Quiz 8",
        description = "Sobre os medicamentos cardiovasculares.",
        questionEntityList = listOf(
            QuizQuestion(
                id = "8",
                problem = "Qual medicamento é usado para evitar coágulos em pacientes cardíacos?",
                alternativeA = "Aspirina",
                alternativeB = "Paracetamol",
                alternativeC = "Ibuprofeno",
                alternativeD = "Antibióticos",
                answer = "Aspirina"
            )
        )
    )
)