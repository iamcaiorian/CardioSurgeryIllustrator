package com.example.cardiosurgeryillustrator.ui.screens.patient.form

import com.example.cardiosurgeryillustrator.ui.components.patient.form.CheckboxGroup
import com.example.cardiosurgeryillustrator.ui.components.patient.form.RadioGroup
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.models.Question
import com.example.cardiosurgeryillustrator.models.QuestionType
import com.example.cardiosurgeryillustrator.models.mock.mockQuestions
import com.example.cardiosurgeryillustrator.ui.components.patient.form.TextInputField
import com.example.cardiosurgeryillustrator.ui.components.button.ConfirmationButton

@Composable
fun CardioForm(
    onNavigateToHome: () -> Unit, onBack: () -> Unit, questionsList: List<Question>
) {

    var answers by remember { mutableStateOf(mutableMapOf<String, String>()) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack, modifier = Modifier.padding(start = 0.dp)) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar"
                        )
                    }
                    Spacer(modifier = Modifier.width(80.dp))
                    Image(
                        painter = painterResource(id = R.drawable.heart_icon),
                        contentDescription = "App Logo",
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Como vai sua saúde?", style = TextStyle(
                        color = Color(0xFF0369A1), fontSize = 24.sp, fontWeight = FontWeight.Bold
                    ), modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        items(questionsList) { question ->
            when (question.type) {
                QuestionType.TEXTINPUT -> {
                    val value = answers[question.id.toString()] ?: ""
                    TextInputField(label = question.text,
                        modifier = Modifier.padding(vertical = 32.dp),
                        value = value,
                        onValueChange = { newValue ->
                            // Crie um novo mapa com as alterações
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

        item {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                ConfirmationButton(text = "Confirmar", onClick = onNavigateToHome)
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
    CardioForm(onNavigateToHome = {}, onBack = {}, questionsList = mockQuestions)
}
