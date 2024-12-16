package com.example.cardiosurgeryillustrator.ui.screens.patient.form

import com.example.cardiosurgeryillustrator.ui.components.CheckboxGroup
import com.example.cardiosurgeryillustrator.ui.components.RadioGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.components.TextInputField

@Composable
fun CardioForm() {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf("") }

    var selectedConditions by remember { mutableStateOf(listOf<String>()) }
    var otherConditionText by remember { mutableStateOf("") }

    var selectedCardioConditions by remember { mutableStateOf(listOf<String>()) }
    var otherCardioConditions by remember { mutableStateOf("") }

    var thromboEvent by remember { mutableStateOf("") }
    var otherThromboEvent by remember { mutableStateOf("") }

    var familyHistory by remember { mutableStateOf("") }
    var otherFamilyHistory by remember { mutableStateOf("") }

    var regularMedications by remember { mutableStateOf(listOf<String>()) }
    var otherRegularMedications by remember { mutableStateOf("") }

    var previousProcedures by remember { mutableStateOf("") }
    var otherPreviousProcedures by remember { mutableStateOf("") }

    var activityLevel by remember { mutableStateOf("") }

    var smokingStatus by remember { mutableStateOf("") }

    var hypertensionHistory by remember { mutableStateOf("") }

    var diabetesHistory by remember { mutableStateOf("") }

    var dyslipidemiaHistory by remember { mutableStateOf("") }

    var surgicalCondition by remember { mutableStateOf("") }
    var otherSurgicalCondition by remember { mutableStateOf("") }

    var recommendedSurgery by remember { mutableStateOf("") }
    var otherRecommendSurgery by remember { mutableStateOf("") }

    var preOpExams by remember { mutableStateOf(listOf<String>()) }
    var otherPreOpExams by remember { mutableStateOf("") }

    var surgeryConcerns by remember { mutableStateOf("") }
    var otherSurgeryConcerns by remember { mutableStateOf("") }

    var educationPreference by remember { mutableStateOf("") }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        item {
            Column(
                modifier = Modifier.fillMaxWidth().padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.heart_icon),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Como vai sua saúde ?",
                    style = TextStyle(
                        color = Color(0xFF0369A1),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }


        item {
            Text(
                "Seção 1: Informações básicas",
                style = TextStyle(
                    color = Color(0xFF049454F),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            TextInputField(
                label = "Qual é a sua altura? (Digite em metros, ex: 1.65)",
                value = height,
                onValueChange = { newHeight ->
                    height = newHeight
                    imc = calculateIMC(newHeight, weight)
                }
            )
            TextInputField(
                label = "Qual é o seu peso atual? (Digite em quilos, ex: 68)",
                value = weight,
                onValueChange = { newWeight ->
                    weight = newWeight
                    imc = calculateIMC(height, newWeight)
                }
            )
            Text("IMC: $imc", modifier = Modifier.padding(bottom = 8.dp))
        }

        item {
            Text(
                "Seção 2: Histórico de saúde atual e progressão",
                style = TextStyle(
                    color = Color(0xFF049454F),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            )

            CheckboxGroup(
                label = "Você tem alguma das seguintes condições de saúde? (Marque todas as opções que se aplicam.)",
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
                selectedOptions = selectedConditions,
                onOptionToggled = { option ->
                    toggleOption(option, selectedConditions) { newSelectedOptions ->
                        selectedConditions = newSelectedOptions
                    }
                },
                onOtherTextChanged = { otherText ->
                    otherConditionText = otherText
                }
            )

            CheckboxGroup(
                label = "Você já foi diagnosticado com alguma condição cardiovascular no passado? (Marque todas as opções que se aplicam.)",
                options = listOf(
                    "Infarto agudo do miocárdio",
                    "Angina estável ou instável",
                    "Insuficiência cardíaca",
                    "Arritmia cardíaca (ex.: fibrilação atrial, taquicardia ventricular)",
                    "Doença arterial periférica",
                    "Miocardite ou pericardite",
                    "Outra"
                ),
                selectedOptions = selectedCardioConditions,
                onOptionToggled = { option ->
                    toggleOption(option, selectedCardioConditions) { newSelectedOptions ->
                        selectedCardioConditions = newSelectedOptions
                    }
                },
                onOtherTextChanged = { otherText ->
                    otherCardioConditions = otherText
                }
            )

            RadioGroup(
                label = "Você já apresentou algum evento tromboembólico no passado?",
                options = listOf("Não", "Sim, trombose venosa profunda (TVP)", "Sim, embolia pulmonar"),
                selectedOption = thromboEvent,
                onOptionSelected = { option ->
                    thromboEvent = option
                    if (option != "Outro") {
                        otherThromboEvent = ""
                    }
                },
                otherOptionText = { otherText ->
                    otherThromboEvent = otherText
                }
            )

            RadioGroup(
                label = "Você tem histórico familiar de doenças cardiovasculares?",
                options = listOf(
                    "Não",
                    "Sim, infarto ou angina antes dos 55 anos (parentes masculinos)",
                    "Sim, infarto ou angina antes dos 65 anos (parentes femininos)",
                    "Outra"
                ),
                selectedOption = familyHistory,
                onOptionSelected = { option ->
                    familyHistory = option
                    if (option != "Outra") {
                        otherFamilyHistory = ""
                    }
                },
                otherOptionText = { otherText ->
                    otherFamilyHistory = otherText
                }
            )

            CheckboxGroup(
                label = "Você faz uso de medicações regularmente? (Marque todas as opções que se aplicam.)",
                options = listOf(
                    "Não faço uso de medicação regular",
                    "Antipertensivos (ex.: losartana, espironolactona, enalapril)",
                    "Estatinas ou outros redutores de colesterol",
                    "Antidiabéticos (ex.: metformina, glifage, insulina)",
                    "Anticoagulantes (ex.: varfarina, rivaroxabana)",
                    "Antiagregantes plaquetários (ex.: AAS, clopidogrel)",
                    "Outra"
                ),
                selectedOptions = regularMedications,
                onOptionToggled = { option ->
                    toggleOption(option, regularMedications) { newSelectedOptions ->
                        regularMedications = newSelectedOptions
                    }
                },
                onOtherTextChanged = { otherText ->
                    otherRegularMedications = otherText
                }
            )

            RadioGroup(
                label = "Você já passou por algum procedimento cardiovascular anteriormente?",
                options = listOf(
                    "Não",
                    "Sim, angioplastia com stent",
                    "Sim, cirurgia de revascularização do miocárdio (pontes de safena ou mamária)",
                    "Sim, troca ou reparo valvar",
                    "Sim, implante de marcapasso ou desfibrilador cardíaco",
                    "Outra"
                ),
                selectedOption = previousProcedures,
                onOptionSelected = { option ->
                    previousProcedures = option
                    if (option != "Outra") {
                        otherPreviousProcedures = ""
                    }
                },
                otherOptionText = { otherText ->
                    otherPreviousProcedures = otherText
                }
            )

            RadioGroup(
                label = "Qual é o seu nível de atividade física?",
                options = listOf(
                    "Sedentário (não faço atividades físicas)",
                    "Atividade leve (ex.: caminhadas ocasionais, tarefas leves)",
                    "Atividade moderada (ex.: exercícios 23 vezes por semana)",
                    "Atividade intensa (ex.: exercícios vigorosos mais de 3 vezes por semana)",
                    "Atividade muito intensa (ex.: treino diário de alta performance)"
                ),
                selectedOption = activityLevel,
                onOptionSelected = { option ->
                    activityLevel = option
                }
            )

            RadioGroup(
                label = "Você é fumante?",
                options = listOf(
                    "Nunca fumei",
                    "Sim, atualmente",
                    "Não, mas já fui fumante e parei há menos de 1 ano",
                    "Não, mas já fui fumante e parei há mais de 1 ano"
                ),
                selectedOption = smokingStatus,
                onOptionSelected = { option ->
                    smokingStatus = option
                }
            )

            RadioGroup(
                label = "Você tem histórico de hipertensão arterial?",
                options = listOf(
                    "Não",
                    "Sim, controlada com medicação",
                    "Sim, mas sem controle adequado"
                ),
                selectedOption = hypertensionHistory,
                onOptionSelected = { option ->
                    hypertensionHistory = option
                }
            )

            RadioGroup(
                label = "Você tem histórico de diabetes?",
                options = listOf(
                    "Não",
                    "Sim, diabetes tipo 1",
                    "Sim, diabetes tipo 2, controlada com dieta/exercício",
                    "Sim, diabetes tipo 2, controlada com medicação",
                    "Sim, diabetes tipo 2, sem controle adequado"
                ),
                selectedOption = diabetesHistory,
                onOptionSelected = { option ->
                    diabetesHistory = option
                }
            )

            RadioGroup(
                label = "Você tem histórico de dislipidemia (colesterol ou triglicerídeos elevados)?",
                options = listOf(
                    "Não",
                    "Sim, com controle adequado (dieta/medicação)",
                    "Sim, sem controle adequado"
                ),
                selectedOption = dyslipidemiaHistory,
                onOptionSelected = { option ->
                    dyslipidemiaHistory = option
                }
            )
        }

        item {
            Text(
                "Seção 3: Condição Atual e Cirurgia",
                style = TextStyle(
                    color = Color(0xFF049454F),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            )

            RadioGroup(
                label = "Qual é a sua condição cirúrgica diagnosticada?",
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
                selectedOption =
                surgicalCondition,
                onOptionSelected = { option ->
                    surgicalCondition = option
                    if (option != "Outra") {
                        otherSurgicalCondition = ""
                    }
                },
                otherOptionText = { otherText ->
                    otherSurgicalCondition = otherText
                }
            )

            RadioGroup(
                label = "Qual cirurgia foi recomendada?",
                options = listOf(
                    "Revascularização do miocárdio (pontes de safena ou mamária)",
                    "Troca valvar (válvula mitral, aórtica ou tricúspide)",
                    "Reparação valvar (válvula mitral, aórtica ou tricúspide)",
                    "Cirurgia para dissecção aórtica",
                    "Correção de aneurisma aórtico",
                    "Implante transcateter de válvula aórtica (TAVI)",
                    "Outra"
                ),
                selectedOption = recommendedSurgery,
                onOptionSelected = { option ->
                    recommendedSurgery = option
                    if (option != "Outra") {
                        otherRecommendSurgery = ""
                    }
                },
                otherOptionText = { otherText ->
                    otherRecommendSurgery = otherText
                }
            )

            CheckboxGroup(
                label = "Você já realizou os exames pré-operatórios? (Marque todas as opções que se aplicam.)",
                options = listOf(
                    "Eletrocardiograma",
                    "Ecocardiograma",
                    "Tomografia computadorizada",
                    "Angiografia coronária",
                    "Hemograma completo",
                    "Exame de função renal",
                    "Outra"
                ),
                selectedOptions = preOpExams,
                onOptionToggled = { option ->
                    toggleOption(option, preOpExams) { newSelectedOptions ->
                        preOpExams = newSelectedOptions
                    }
                },
                onOtherTextChanged = { otherText ->
                    otherPreOpExams = otherText
                }
            )
        }

        item {
            Text(
                "Seção 4: Aspectos Pessoais e Educação",
                style = TextStyle(
                    color = Color(0xFF049454F),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            RadioGroup(
                label = "Qual é a sua principal preocupação em relação à cirurgia?",
                options = listOf(
                    "Riscos do procedimento",
                    "Efeitos colaterais ou complicações",
                    "Tempo de recuperação",
                    "Qualidade de vida após a cirurgia",
                    "Custo do procedimento",
                    "Outra"
                ),
                selectedOption = surgeryConcerns,
                onOptionSelected = { option ->
                    surgeryConcerns = option
                    if (option != "Outra") {
                        otherSurgeryConcerns = ""
                    }
                },
                otherOptionText = { otherText ->
                    otherSurgeryConcerns = otherText
                }
            )

            RadioGroup(
                label = "Você gostaria de receber orientações detalhadas sobre o procedimento cirúrgico?",
                options = listOf(
                    "Sim, com ilustrações detalhadas e explicações passo a passo",
                    "Sim, mas de forma resumida e direta",
                    "Não, já estou bem informado(a)",
                    "Gostaria de discutir apenas com o meu médico"
                ),
                selectedOption = educationPreference,
                onOptionSelected = { option ->
                    educationPreference = option

                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0369A1),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp)
                    .width(120.dp)
            ) {
                Text("Confirmar")
            }
        }
    }
}

fun toggleOption(
    option: String,
    selectedConditions: List<String>,
    setSelectedOptions: (List<String>) -> Unit
) {
    val newSelectedOptions = if (selectedConditions.contains(option)) {
        selectedConditions - option
    } else {
        selectedConditions + option
    }
    setSelectedOptions(newSelectedOptions)
}



fun calculateIMC(height: String, weight: String): String {
    val h = height.toDoubleOrNull()
    val w = weight.toDoubleOrNull()
    return if (h != null && w != null && h > 0) {
        String.format("%.2f", w / (h * h))
    } else {
        "Altura ou peso inválido"
    }
}
