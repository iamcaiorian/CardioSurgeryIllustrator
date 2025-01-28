package com.example.cardiosurgeryillustrator.models.mock.student

import com.example.cardiosurgeryillustrator.models.student.quiz.Quiz

val mockQuizzes = listOf(
    Quiz(
        id = "1",
        title = "Quiz 1",
        subtitle = "Anatomia do Coração",
        question = "Quantas câmaras o coração humano possui?",
        description = "Detalhes sobre a anatomia básica do coração humano.",
        options = listOf("2", "3", "4", "5"),
        correctAnswer = "4"
    ),
    Quiz(
        id = "2",
        title = "Quiz 2",
        subtitle = "Eletrocardiograma (ECG)",
        question = "O que o ECG mede?",
        description = "Explicação sobre o que o eletrocardiograma monitora.",
        options = listOf(
            "Pressão arterial",
            "Atividade elétrica do coração",
            "Quantidade de sangue bombeado",
            "Níveis de oxigênio no sangue"
        ),
        correctAnswer = "Atividade elétrica do coração"
    ),
    Quiz(
        id = "3",
        title = "Quiz 3",
        subtitle = "Insuficiência Cardíaca",
        question = "Qual é um dos sintomas mais comuns da insuficiência cardíaca?",
        description = "Sobre os sinais de insuficiência cardíaca.",
        options = listOf(
            "Falta de ar ao realizar esforços",
            "Dor intensa no peito",
            "Desmaios frequentes",
            "Palidez"
        ),
        correctAnswer = "Falta de ar ao realizar esforços"
    ),
    Quiz(
        id = "4",
        title = "Quiz 4",
        subtitle = "Hipertensão Arterial",
        question = "O que caracteriza a hipertensão arterial?",
        description = "Compreensão dos níveis de pressão arterial.",
        options = listOf(
            "Pressão arterial abaixo de 120/80",
            "Pressão arterial acima de 140/90",
            "Redução no fluxo sanguíneo coronário",
            "Pressão constante de 110/70"
        ),
        correctAnswer = "Pressão arterial acima de 140/90"
    ),
    Quiz(
        id = "5",
        title = "Quiz 5",
        subtitle = "Doença Arterial Coronariana",
        question = "O que causa a doença arterial coronariana?",
        description = "Causas comuns dessa condição cardíaca.",
        options = listOf(
            "Infecção viral no coração",
            "Acúmulo de placas de gordura nas artérias coronárias",
            "Ritmo cardíaco irregular",
            "Anemia severa"
        ),
        correctAnswer = "Acúmulo de placas de gordura nas artérias coronárias"
    ),
    Quiz(
        id = "6",
        title = "Quiz 6",
        subtitle = "Cirurgia Cardiovascular",
        question = "Qual é o objetivo principal de uma cirurgia de bypass coronário?",
        description = "Sobre cirurgias cardiovasculares.",
        options = listOf(
            "Reduzir a pressão arterial",
            "Substituir válvulas danificadas",
            "Desviar o fluxo sanguíneo em torno de obstruções arteriais",
            "Monitorar o ritmo cardíaco"
        ),
        correctAnswer = "Desviar o fluxo sanguíneo em torno de obstruções arteriais"
    ),
    Quiz(
        id = "7",
        title = "Quiz 7",
        subtitle = "Reabilitação Cardiovascular",
        question = "Qual é um dos principais benefícios da reabilitação cardiovascular?",
        description = "Importância da reabilitação para pacientes cardíacos.",
        options = listOf(
            "Aumento da pressão arterial",
            "Redução do colesterol LDL",
            "Melhora da capacidade física e qualidade de vida",
            "Substituição de válvulas cardíacas"
        ),
        correctAnswer = "Melhora da capacidade física e qualidade de vida"
    ),
    Quiz(
        id = "8",
        title = "Quiz 8",
        subtitle = "Farmacologia Cardiovascular",
        question = "Qual medicamento é usado para evitar coágulos em pacientes cardíacos?",
        description = "Sobre os medicamentos cardiovasculares.",
        options = listOf("Aspirina", "Paracetamol", "Ibuprofeno", "Antibióticos"),
        correctAnswer = "Aspirina"
    )
)
