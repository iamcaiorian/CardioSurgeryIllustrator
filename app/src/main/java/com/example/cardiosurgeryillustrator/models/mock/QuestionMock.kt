package com.example.cardiosurgeryillustrator.models.mock

import com.example.cardiosurgeryillustrator.models.student.quiz.Question
import com.example.cardiosurgeryillustrator.models.student.quiz.QuestionType


val mockQuestions = listOf(
        Question(
            id = 1,
            text = "Qual é a sua altura? (Digite em metros, ex: 1.65)",
            type = QuestionType.TEXTINPUT
        ),
        Question(
            id = 2,
            text = "Qual é o seu peso atual? (Digite em quilos, ex: 68)",
            type = QuestionType.TEXTINPUT
        ),
        Question(
            id = 3,
            text = "Você tem alguma das seguintes condições de saúde? (Marque todas as opções que se aplicam.)",
            options = listOf(
                "Diabetes tipo 1",
                "Diabetes tipo 2",
                "Hipertensão arterial leve",
                "Hipertensão arterial moderada ou grave",
                "Insuficiência cardíaca congestiva",
                "Doença arterial coronariana",
                "Dislipidemia (colesterol ou triglicerídeos elevados)",
                "Doença renal crônica",
                "Doença pulmonar obstrutiva crônica (DPOC)",
                "Outra"
            ),
            type = QuestionType.CHECKBOX
        ),
        Question(
            id = 4,
            text = "Você já foi diagnosticado com alguma condição cardiovascular no passado? (Marque todas as opções que se aplicam.)",
            options = listOf(
                "Infarto agudo do miocárdio",
                "Angina estável ou instável",
                "Insuficiência cardíaca",
                "Arritmia cardíaca (ex.: fibrilação atrial, taquicardia ventricular)",
                "Doença arterial periférica",
                "Miocardite ou pericardite",
                "Outra"
            ),
            type = QuestionType.CHECKBOX
        ),
        Question(
            id = 5,
            text = "Você já apresentou algum evento tromboembólico no passado?",
            options = listOf("Não", "Sim, trombose venosa profunda (TVP)", "Sim, embolia pulmonar"),
            type = QuestionType.RADIOBUTTON
        ),
        Question(
            id = 6,
            text = "Você tem histórico familiar de doenças cardiovasculares?",
            options = listOf(
                "Não",
                "Sim, infarto ou angina antes dos 55 anos (parentes masculinos)",
                "Sim, infarto ou angina antes dos 65 anos (parentes femininos)",
                "Outra"
            ),
            type = QuestionType.RADIOBUTTON
        ),
        Question(
            id = 7,
            text = "Você faz uso de medicações regularmente? (Marque todas as opções que se aplicam.)",
            options = listOf(
                "Não faço uso de medicação regular",
                "Antipertensivos (ex.: losartana, espironolactona, enalapril)",
                "Estatinas ou outros redutores de colesterol",
                "Antidiabéticos (ex.: metformina, glifage, insulina)",
                "Anticoagulantes (ex.: varfarina, rivaroxabana)",
                "Antiagregantes plaquetários (ex.: AAS, clopidogrel)",
                "Outra"
            ),
            type = QuestionType.CHECKBOX
        ),
        Question(
            id = 8,
            text = "Você já passou por algum procedimento cardiovascular anteriormente?",
            options = listOf(
                "Não",
                "Sim, angioplastia com stent",
                "Sim, cirurgia de revascularização do miocárdio (pontes de safena ou mamária)",
                "Sim, troca ou reparo valvar",
                "Sim, implante de marcapasso ou desfibrilador cardíaco",
                "Outra"
            ),
            type = QuestionType.RADIOBUTTON
        ),
        Question(
            id = 9,
            text = "Qual é o seu nível de atividade física?",
            options = listOf(
                "Sedentário (não faço atividades físicas)",
                "Atividade leve (ex.: caminhadas ocasionais, tarefas leves)",
                "Atividade moderada (ex.: exercícios 23 vezes por semana)",
                "Atividade intensa (ex.: exercícios vigorosos mais de 3 vezes por semana)",
                "Atividade muito intensa (ex.: treino diário de alta performance)"
            ),
            type = QuestionType.RADIOBUTTON
        ),
        Question(
            id = 10,
            text = "Você é fumante?",
            options = listOf(
                "Nunca fumei",
                "Sim, atualmente",
                "Não, mas já fui fumante e parei há menos de 1 ano",
                "Não, mas já fui fumante e parei há mais de 1 ano"
            ),
            type = QuestionType.RADIOBUTTON
        ),
        Question(
            id = 11,
            text = "Você tem histórico de hipertensão arterial?",
            options = listOf(
                "Não",
                "Sim, controlada com medicação",
                "Sim, mas sem controle adequado"
            ),
            type = QuestionType.RADIOBUTTON
        ),
        Question(
            id = 12,
            text = "Você tem histórico de diabetes?",
            options = listOf(
                "Não",
                "Sim, diabetes tipo 1",
                "Sim, diabetes tipo 2, controlada com dieta/exercício",
                "Sim, diabetes tipo 2, controlada com medicação",
                "Sim, diabetes tipo 2, sem controle adequado"
            ),
            type = QuestionType.RADIOBUTTON
        ),
        Question(
            id = 13,
            text = "Você tem histórico de dislipidemia (colesterol ou triglicerídeos elevados)?",
            options = listOf(
                "Não",
                "Sim, com controle adequado (dieta/medicação)",
                "Sim, sem controle adequado"
            ),
            type = QuestionType.RADIOBUTTON
        ),
        Question(
            id = 14,
            text = "Qual é a sua condição cirúrgica diagnosticada?",
            options = listOf(
                "Doença coronariana (entupimento das artérias do coração)",
                "Valvopatia mitral (problemas na válvula mitral)",
                "Valvopatia aórtica (problemas na válvula aórtica)",
                "Valvopatia tricúspide (problemas na válvula tricúspide)",
                "Dissecção aórtica",
                "Aneurisma de aorta torácica",
                "Aneurisma de aorta abdominal",
                "Miocardiopatia dilatada ou hipertrófica",
                "Outra"
            ),
            type = QuestionType.RADIOBUTTON
        ),
        Question(
            id = 15,
            text = "Qual cirurgia foi recomendada?",
            options = listOf(
                "Revascularização do miocárdio (pontes de safena ou mamária)",
                "Troca valvar (válvula mitral, aórtica ou tricúspide)",
                "Reparação valvar (válvula mitral, aórtica ou tricúspide)",
                "Cirurgia para dissecção aórtica",
                "Correção de aneurisma aórtico",
                "Implante transcateter de válvula aórtica (TAVI)",
                "Outra"
            ),
            type = QuestionType.RADIOBUTTON
        ),
        Question(
            id = 16,
            text = "Você já realizou os exames pré-operatórios? (Marque todas as opções que se aplicam.)",
            options = listOf(
                "Eletrocardiograma",
                "Ecocardiograma",
                "Tomografia computadorizada",
                "Angiografia coronária",
                "Hemograma completo",
                "Exame de função renal",
                "Outra"
            ),
            type = QuestionType.CHECKBOX
        ),
        Question(
            id = 17,
            text = "Qual é a sua principal preocupação em relação à cirurgia?",
            options = listOf(
                "Riscos do procedimento",
                "Efeitos colaterais ou complicações",
                "Tempo de recuperação",
                "Qualidade de vida após a cirurgia",
                "Custo do procedimento",
                "Outra"
            ),
            type = QuestionType.RADIOBUTTON
        ),
        Question(
            id = 18,
            text = "Você gostaria de receber orientações detalhadas sobre o procedimento cirúrgico?",
            options = listOf(
                "Sim, com ilustrações detalhadas e explicações passo a passo",
                "Sim, mas de forma resumida e direta",
                "Não, já estou bem informado(a)",
                "Gostaria de discutir apenas com o meu médico"
            ),
            type = QuestionType.RADIOBUTTON
        )
)