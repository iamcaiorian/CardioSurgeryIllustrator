package com.example.cardiosurgeryillustrator.ui.screens.patient.form

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.mock.patient.mockQuestions
import com.example.cardiosurgeryillustrator.models.student.quiz.question.Question
import com.example.cardiosurgeryillustrator.models.student.quiz.question.QuestionType
import com.example.cardiosurgeryillustrator.navigation.BottomBarPacientAction
import com.example.cardiosurgeryillustrator.ui.components.buttons.ButtonDefault
import com.example.cardiosurgeryillustrator.ui.components.patient.form.CheckboxGroup
import com.example.cardiosurgeryillustrator.ui.components.patient.form.RadioGroup
import com.example.cardiosurgeryillustrator.ui.components.patient.form.TextInputField
import com.example.cardiosurgeryillustrator.ui.theme.Zinc500
import com.example.cardiosurgeryillustrator.view_models.patient.form.CardioFormViewModel
import getQuestionIndex
import removeAccents

@Composable
fun CardioForm(
    navController: NavController,
    questionsList: List<Question>,
    viewModel: CardioFormViewModel = CardioFormViewModel(LocalContext.current),
    modifier: Modifier = Modifier
) {
    var currentIndex by remember { mutableStateOf(0) }
    var answers by remember { mutableStateOf(mutableMapOf<String, String>()) }

    val currentQuestion = questionsList.getOrNull(currentIndex)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header com botão de voltar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.heart_icon),
                contentDescription = "App Logo",
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Como vai sua saúde?", style = TextStyle(
                color = Color(0xFF0369A1), fontSize = 24.sp, fontWeight = FontWeight.Bold
            ), modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        currentQuestion?.let { question ->
            when (question.type) {
                QuestionType.TEXTINPUT -> {
                    val value = answers[question.id.toString()] ?: ""
                    TextInputField(label = question.text,
                        modifier = Modifier.padding(vertical = 32.dp),
                        value = value,
                        onValueChange = { newValue ->
                            answers = answers.toMutableMap().apply {
                                this[question.id.toString()] = newValue
                            }
                        })
                }

                QuestionType.CHECKBOX -> {
                    val selectedOptions = answers[question.id.toString()]?.split(";") ?: emptyList()
                    CheckboxGroup(
                        label = question.text,
                        options = question.options ?: emptyList(),
                        selectedOptions = selectedOptions,
                        onOptionToggled = { option ->
                            val updatedOptions = selectedOptions.toMutableList().apply {
                                if (contains(option)) remove(option) else add(option)
                            }
                            answers = answers.toMutableMap().apply {
                                this[question.id.toString()] = updatedOptions.joinToString(";")
                            }
                        },
                        onOtherTextChanged = { otherText ->
                            answers = answers.toMutableMap().apply {
                                this["${question.id}_other"] = otherText
                            }
                        },
                        otherText = answers["${question.id}_other"] ?: ""
                    )
                }

                QuestionType.RADIOBUTTON -> {
                    val selectedOption = answers[question.id.toString()] ?: ""
                    RadioGroup(
                        label = question.text,
                        options = question.options ?: emptyList(),
                        selectedOption = selectedOption,
                        onOptionSelected = { selected ->
                            answers = answers.toMutableMap().apply {
                                this[question.id.toString()] = selected
                            }
                        },
                        otherOptionText = { otherText ->
                            answers = answers.toMutableMap().apply {
                                this["${question.id}_other"] = otherText
                            }
                        },
                        otherText = answers["${question.id}_other"] ?: ""
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))


        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (currentIndex > 0) {
                ButtonDefault(
                    text = "Anterior", onClick = { currentIndex-- }, isIcon = false, color = Zinc500
                )
            }

            if (currentIndex < questionsList.size - 1) {
                ButtonDefault(
                    text = "Próximo", onClick = { currentIndex++ }, isIcon = true
                )
            } else {
                ButtonDefault(
                    text = "Finalizar",
                    onClick = {
                        Log.d("Asnwers Array", answers.toString())

                        // Salvar a resposta da questão 14
                        answers["14"]?.let { response ->
                            viewModel.saveQuestion14Response(response)
                        }

                        val height = answers["1"] ?: ""
                        val weight = answers["2"] ?: ""
                        val disease = answers["14"] ?: ""
                        Log.d("doença", disease)

                        val diseaseIndex = getQuestionIndex(disease)
                        val diseaseWithoutAccents = removeAccents(disease)
                        Log.d("doença sem acentos", diseaseWithoutAccents)

                        viewModel.saveUserDiseaseIndex(diseaseIndex)

                        val imcResult = calculateIMC(height, weight)
                        if (imcResult != "Altura ou peso inválido") {
                            viewModel.saveIMC(imcResult)
                        }

                        viewModel.saveFormResponse()

                        navController.navigate("${BottomBarPacientAction.HomePacient.route}/$diseaseIndex")
                    },
                    isIcon = true
                )

            }
        }
    }
}

fun toggleOption(
    option: String, selectedConditions: List<String>, setSelectedOptions: (List<String>) -> Unit
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

@Preview
@Composable
private fun CardioFormPreview() {
    CardioForm(
        navController = rememberNavController(),
        questionsList = mockQuestions,
        modifier = Modifier,
        viewModel = CardioFormViewModel(
            LocalContext.current
        )
    )
}
