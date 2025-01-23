package com.example.cardiosurgeryillustrator.models.mock

import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.patient.home.Artery
import com.example.cardiosurgeryillustrator.models.InfoText

val mockArtery = listOf(
    Artery(
        id = "right_coronary",
        arteryName = "Coronária Direita",
        imageRes = R.drawable.right_coronary,
        texts = listOf(
            InfoText(
                id = 1,
                title = "Artéria entupida",
                subtitle = "Acúmulo de placas de gordura",
                content = "O principal responsável pelo entupimento das artérias é o depósito de " +
                        "placas de gordura (lipídios), principalmente colesterol LDL (\"colesterol ruim\")." +
                        " Quando esses depósitos se acumulam na parede arterial, eles formam placas " +
                        "que estreitam o diâmetro interno da artéria, dificultando o fluxo sanguíneo."
            ),
            InfoText(
                id = 2,
                title = "Fatores de Risco",
                subtitle = "Coisas que devemos ficar atentos",
                content = "Dieta rica em gorduras saturadas e trans aumenta os níveis de colesterol LDL," +
                        " sedentarismo diminui a capacidade do corpo de metabolizar gorduras, " +
                        "tabagismo danifica a parede arterial e aumenta o risco de inflamação, " +
                        "altos níveis de glicose no sangue favorecem o acúmulo de gordura nas artérias, " +
                        "pressão alta força as paredes das artérias, tornando-as mais vulneráveis a lesões, " +
                        "e obesidade está associada a níveis elevados de colesterol e pressão arterial."
            )
        )
    ),
    Artery(
        id = "left_coronary",
        arteryName = "Coronária Esquerda",
        imageRes = R.drawable.left_coronary,
        texts = listOf(
            InfoText(
                id = 1,
                title = "Risco de Infarto",
                subtitle = "Complicação do entupimento arterial",
                content = "O entupimento das artérias pode levar a sérias complicações, como o infarto do miocárdio. " +
                        "Esse problema ocorre quando uma das artérias coronárias é completamente bloqueada, impedindo o fluxo " +
                        "de sangue e oxigênio para uma parte do coração. Esse bloqueio é frequentemente causado por placas de " +
                        "gordura ou coágulos formados sobre essas placas."
            ),
            InfoText(
                id = 2,
                title = "Fatores de Risco",
                subtitle = "Contribuintes para o entupimento arterial",
                content = "Diversos fatores aumentam o risco de entupimento das artérias, incluindo hábitos de vida e condições " +
                        "de saúde. Entre os principais fatores estão o tabagismo, dieta rica em gorduras saturadas, sedentarismo, " +
                        "obesidade, hipertensão arterial, diabetes e histórico familiar de doenças cardiovasculares. A combinação " +
                        "desses fatores pode acelerar o acúmulo de placas de gordura nas artérias."
            )

        )
    )
)