package com.example.cardiosurgeryillustrator.models.mock.patient

import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.patient.home.Artery
import com.example.cardiosurgeryillustrator.models.patient.home.InfoText

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
    ),
    Artery(
        id = "mitral_valve",
        arteryName = "Válvula Mitral",
        imageRes = R.drawable.right_coronary,
        texts = listOf(
            InfoText(
                id = 1,
                title = "Valvopatia Mitral",
                subtitle = "Problemas na válvula mitral",
                content = "A valvopatia mitral é caracterizada por problemas na válvula que regula o fluxo sanguíneo entre o átrio esquerdo e o ventrículo esquerdo do coração. Isso pode resultar em fluxo sanguíneo anormal, o que pode levar a insuficiência cardíaca e aumento da pressão pulmonar."
            ),
            InfoText(
                id = 2,
                title = "Fatores de Risco",
                subtitle = "Fatores que causam problemas na válvula mitral",
                content = "Infecções como febre reumática, hipertensão e envelhecimento podem levar à degeneração da válvula mitral, resultando em um mau funcionamento da válvula e comprometendo o fluxo sanguíneo."
            )
        )
    ),
    Artery(
        id = "aortic_valve",
        arteryName = "Válvula Aórtica",
        imageRes = R.drawable.left_coronary,
        texts = listOf(
            InfoText(
                id = 1,
                title = "Valvopatia Aórtica",
                subtitle = "Problemas na válvula aórtica",
                content = "A valvopatia aórtica é caracterizada pelo mau funcionamento da válvula aórtica, que pode afetar a circulação sanguínea para o resto do corpo. Isso pode levar a sintomas como falta de ar, dor no peito e cansaço, além de aumentar o risco de infarto."
            ),
            InfoText(
                id = 2,
                title = "Fatores de Risco",
                subtitle = "Fatores que prejudicam a válvula aórtica",
                content = "Fatores como hipertensão, doenças cardíacas hereditárias, infecções e o envelhecimento podem afetar a válvula aórtica e levar a problemas de saúde graves."
            )
        )
    ),
    Artery(
        id = "aortic_dissection",
        arteryName = "Dissecção Aórtica",
        imageRes = R.drawable.right_coronary,
        texts = listOf(
            InfoText(
                id = 1,
                title = "Dissecção Aórtica",
                subtitle = "Ruptura da parede da aorta",
                content = "A dissecção aórtica é uma condição grave em que ocorre uma ruptura na parede da aorta, a maior artéria do corpo. Isso pode causar dor intensa no peito e levar a complicações fatais se não tratado rapidamente."
            ),
            InfoText(
                id = 2,
                title = "Fatores de Risco",
                subtitle = "Causas da dissecção aórtica",
                content = "Hipertensão, aneurismas aórticos, doenças do tecido conectivo e histórico familiar aumentam o risco de dissecção aórtica."
            )
        )
    ),
    Artery(
        id = "abdominal_aneurysm",
        arteryName = "Aneurisma de Aorta Abdominal",
        imageRes = R.drawable.left_coronary,
        texts = listOf(
            InfoText(
                id = 1,
                title = "Aneurisma Abdominal",
                subtitle = "A dilatação da aorta abdominal",
                content = "O aneurisma de aorta abdominal ocorre quando a aorta se dilata na região abdominal, aumentando o risco de ruptura e hemorragia interna. Essa condição é frequentemente assintomática até que ocorra uma ruptura."
            ),
            InfoText(
                id = 2,
                title = "Fatores de Risco",
                subtitle = "Riscos relacionados ao aneurisma abdominal",
                content = "Fatores como idade avançada, tabagismo, hipertensão e histórico familiar de aneurismas aumentam o risco de desenvolver aneurisma de aorta abdominal."
            )
        )
    ),
    Artery(
        id = "myocardial_disease",
        arteryName = "Miocardiopatia",
        imageRes = R.drawable.right_coronary,
        texts = listOf(
            InfoText(
                id = 1,
                title = "Miocardiopatia Dilatada ou Hipertrófica",
                subtitle = "Doença que afeta o músculo cardíaco",
                content = "A miocardiopatia dilatada é caracterizada pela dilatação e enfraquecimento das paredes do coração, o que prejudica a função cardíaca. A miocardiopatia hipertrófica ocorre quando o músculo cardíaco se torna excessivamente espesso, dificultando o bombeamento eficiente do sangue."
            ),
            InfoText(
                id = 2,
                title = "Fatores de Risco",
                subtitle = "Causas da miocardiopatia",
                content = "Fatores genéticos, hipertensão e doenças cardíacas pré-existentes são fatores que podem contribuir para o desenvolvimento de miocardiopatia dilatada ou hipertrófica."
            )
        )
    )
)