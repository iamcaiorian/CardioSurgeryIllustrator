package com.example.cardiosurgeryillustrator.models.mock

import androidx.compose.runtime.mutableStateOf
import com.example.cardiosurgeryillustrator.models.Module

val mockModules = listOf(
    Module(
        id = "1",
        subjectId = "4",
        title = "Anatomia do Coração",
        description = "Exploração detalhada das câmaras, válvulas, vasos sanguíneos e nervos que compõem o coração humano.",
        cover = "https://example.com/covers/anatomia_coracao.jpg",
        progress = 0.75f, // 75% concluído
        longDescription = """
            O coração humano é um órgão vital composto por quatro câmaras: átrios esquerdo e direito, e ventrículos esquerdo e direito. Ele é responsável por bombear sangue para todo o corpo, garantindo a oxigenação dos tecidos e a remoção de resíduos metabólicos. As válvulas cardíacas, como a mitral e a tricúspide, regulam o fluxo sanguíneo entre as câmaras e impedem o refluxo.
            
            Além disso, o coração está conectado a grandes vasos, como a aorta e a artéria pulmonar, que transportam o sangue para e dos pulmões. Nervos simpáticos e parassimpáticos controlam o ritmo cardíaco, ajustando-o às necessidades do corpo durante o repouso e a atividade física.
        """.trimIndent(),
        isFavorite = mutableStateOf(false)
    ),
    Module(
        id = "2",
        subjectId = "5",
        title = "Eletrocardiograma (ECG)",
        description = "Estudo dos princípios básicos do ECG, incluindo interpretação de ondas, intervalos e ritmos cardíacos.",
        cover = "https://example.com/covers/eletrocardiograma.jpg",
        progress = 0.45f, // 45% concluído
        longDescription = """
            O eletrocardiograma (ECG) é uma ferramenta diagnóstica essencial que registra a atividade elétrica do coração. Ele é composto por diferentes ondas (P, QRS e T), que refletem a despolarização e repolarização dos átrios e ventrículos. A análise dessas ondas ajuda a identificar arritmias, bloqueios cardíacos e outras anormalidades.
            
            Além disso, o ECG é utilizado em emergências para detectar infartos do miocárdio e monitorar a eficácia de tratamentos. É um exame simples, indolor e amplamente utilizado, tornando-se indispensável na prática clínica cardiovascular.
        """.trimIndent(),
        isFavorite = mutableStateOf(false)
    ),
    Module(
        id = "3",
        subjectId = "1",
        title = "Insuficiência Cardíaca",
        description = "Abordagem dos tipos de insuficiência cardíaca, diagnóstico, e opções terapêuticas disponíveis.",
        cover = "https://example.com/covers/insuficiencia_cardiaca.jpg",
        progress = 0.60f, // 60% concluído
        longDescription = """
            A insuficiência cardíaca ocorre quando o coração é incapaz de bombear sangue suficiente para atender às necessidades metabólicas do corpo. Ela pode ser causada por várias condições, como hipertensão, infarto do miocárdio e doenças valvares. Os principais tipos incluem insuficiência cardíaca sistólica e diastólica.
            
            O diagnóstico é baseado em exames clínicos, laboratoriais e de imagem, como o ecocardiograma. As opções terapêuticas variam de medicamentos, como inibidores da ECA e betabloqueadores, a dispositivos, como marcapassos e transplante cardíaco em casos graves.
        """.trimIndent(),
        isFavorite = mutableStateOf(false)
    ),
    Module(
        id = "4",
        subjectId = "1",
        title = "Hipertensão Arterial",
        description = "Compreensão da hipertensão arterial, causas, efeitos no sistema cardiovascular e estratégias de tratamento.",
        cover = "https://example.com/covers/hipertensao_arterial.jpg",
        progress = 0.30f, // 30% concluído
        longDescription = """
            A hipertensão arterial é uma condição crônica caracterizada pelo aumento persistente da pressão arterial. Ela é um dos principais fatores de risco para doenças cardiovasculares, como infarto e acidente vascular cerebral. As causas podem ser primárias (idiopáticas) ou secundárias, relacionadas a doenças renais e endócrinas.
            
            O manejo da hipertensão inclui mudanças no estilo de vida, como dieta balanceada e atividade física, além do uso de medicamentos, como diuréticos, bloqueadores dos canais de cálcio e inibidores da ECA. O controle adequado reduz significativamente o risco de complicações.
        """.trimIndent(),
        isFavorite = mutableStateOf(false)
    ),
    Module(
        id = "5",
        subjectId = "2",
        title = "Doença Arterial Coronariana",
        description = "Análise da fisiopatologia da doença arterial coronariana, diagnóstico e intervenção.",
        cover = "https://example.com/covers/doenca_coronariana.jpg",
        progress = 0.85f, // 85% concluído
        longDescription = """
            A doença arterial coronariana (DAC) ocorre devido ao acúmulo de placas de ateroma nas artérias coronárias, restringindo o fluxo sanguíneo para o músculo cardíaco. Isso pode levar a angina, infarto do miocárdio e insuficiência cardíaca. Os fatores de risco incluem tabagismo, obesidade, diabetes e histórico familiar.
            
            O diagnóstico é feito por meio de exames como o teste de esforço, angiografia coronariana e tomografia. O tratamento envolve mudanças no estilo de vida, medicamentos e, em casos graves, intervenções como angioplastia ou bypass coronariano.
        """.trimIndent(),
        isFavorite = mutableStateOf(false)
    ),
    Module(
        id = "6",
        subjectId = "2",
        title = "Cirurgia Cardiovascular",
        description = "Introdução aos procedimentos cirúrgicos no tratamento de condições cardiovasculares complexas.",
        cover = "https://example.com/covers/cirurgia_cardiovascular.jpg",
        progress = 0.20f, // 20% concluído
        longDescription = """
            A cirurgia cardiovascular abrange uma variedade de procedimentos, incluindo revascularização do miocárdio, correção de valvopatias e transplantes cardíacos. Esses procedimentos são realizados para tratar condições que não podem ser resolvidas por terapias convencionais.
            
            Com avanços tecnológicos, como cirurgia robótica e técnicas minimamente invasivas, o risco de complicações foi reduzido, melhorando significativamente os resultados e a qualidade de vida dos pacientes.
        """.trimIndent(),
        isFavorite = mutableStateOf(false)
    ),
    Module(
        id = "7",
        subjectId = "3",
        title = "Reabilitação Cardiovascular",
        description = "Estudo das práticas de reabilitação para pacientes com condições cardíacas, focando em exercícios e suporte psicológico.",
        cover = "https://example.com/covers/reabilitacao_cardiovascular.jpg",
        progress = 0.50f, // 50% concluído
        longDescription = """
            A reabilitação cardiovascular é um programa estruturado que combina exercícios supervisionados, educação em saúde e suporte psicológico para pacientes que se recuperam de eventos cardíacos. O objetivo é melhorar a capacidade funcional, reduzir fatores de risco e aumentar a qualidade de vida.
            
            Esses programas incluem avaliação inicial, monitoramento contínuo e planos individualizados que envolvem a equipe multidisciplinar, como cardiologistas, fisioterapeutas e nutricionistas.
        """.trimIndent(),
        isFavorite = mutableStateOf(false)
    ),
    Module(
        id = "8",
        subjectId = "3",
        title = "Farmacologia Cardiovascular",
        description = "Estudo dos medicamentos utilizados para tratar doenças cardiovasculares e seus mecanismos de ação.",
        cover = "https://example.com/covers/farmacologia_cardiovascular.jpg",
        progress = 0.10f, // 10% concluído
        longDescription = """
            A farmacologia cardiovascular explora os medicamentos usados para tratar condições cardíacas, como anti-hipertensivos, diuréticos e anticoagulantes. Esses medicamentos ajudam a controlar a pressão arterial, reduzir a carga de trabalho do coração e prevenir complicações, como coágulos sanguíneos.
            
            O conhecimento dos mecanismos de ação e dos efeitos colaterais desses medicamentos é crucial para otimizar o tratamento e garantir a segurança do paciente.
        """.trimIndent(),
        isFavorite = mutableStateOf(false)
    )
)
