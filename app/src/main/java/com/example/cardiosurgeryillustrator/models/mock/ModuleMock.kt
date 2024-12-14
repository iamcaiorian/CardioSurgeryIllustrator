package com.example.cardiosurgeryillustrator.models.mock

import com.example.cardiosurgeryillustrator.models.Module

val mockModules = listOf(
    Module(
        id = "1",
        subjectId = "4",
        title = "Anatomia do Coração",
        description = "Exploração detalhada das câmaras, válvulas, vasos sanguíneos e nervos que compõem o coração humano.",
        cover = "https://example.com/covers/anatomia_coracao.jpg",
        progress = 0.75f // 75% concluído
    ),
    Module(
        id = "2",
        subjectId = "5",
        title = "Eletrocardiograma (ECG)",
        description = "Estudo dos princípios básicos do ECG, incluindo interpretação de ondas, intervalos e ritmos cardíacos.",
        cover = "https://example.com/covers/eletrocardiograma.jpg",
        progress = 0.45f // 45% concluído
    ),
    Module(
        id = "3",
        subjectId = "1",
        title = "Insuficiência Cardíaca",
        description = "Abordagem dos tipos de insuficiência cardíaca, diagnóstico, e opções terapêuticas disponíveis.",
        cover = "https://example.com/covers/insuficiencia_cardiaca.jpg",
        progress = 0.60f // 60% concluído
    ),
    Module(
        id = "4",
        subjectId = "1",
        title = "Hipertensão Arterial",
        description = "Compreensão da hipertensão arterial, causas, efeitos no sistema cardiovascular e estratégias de tratamento.",
        cover = "https://example.com/covers/hipertensao_arterial.jpg",
        progress = 0.30f // 30% concluído
    ),
    Module(
        id = "5",
        subjectId = "2",
        title = "Doença Arterial Coronariana",
        description = "Análise da fisiopatologia da doença arterial coronariana, diagnóstico e intervenção.",
        cover = "https://example.com/covers/doenca_coronariana.jpg",
        progress = 0.85f // 85% concluído
    ),
    Module(
        id = "6",
        subjectId = "2",
        title = "Cirurgia Cardiovascular",
        description = "Introdução aos procedimentos cirúrgicos no tratamento de condições cardiovasculares complexas.",
        cover = "https://example.com/covers/cirurgia_cardiovascular.jpg",
        progress = 0.20f // 20% concluído
    ),
    Module(
        id = "7",
        subjectId = "3",
        title = "Reabilitação Cardiovascular",
        description = "Estudo das práticas de reabilitação para pacientes com condições cardíacas, focando em exercícios e suporte psicológico.",
        cover = "https://example.com/covers/reabilitacao_cardiovascular.jpg",
        progress = 0.50f // 50% concluído
    ),
    Module(
        id = "8",
        subjectId = "3",
        title = "Farmacologia Cardiovascular",
        description = "Estudo dos medicamentos utilizados para tratar doenças cardiovasculares e seus mecanismos de ação.",
        cover = "https://example.com/covers/farmacologia_cardiovascular.jpg",
        progress = 0.10f // 10% concluído
    )
)
