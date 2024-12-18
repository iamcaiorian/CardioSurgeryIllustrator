package com.example.cardiosurgeryillustrator.ui.components.patient.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardiosurgeryillustrator.ui.screens.patient.form.toggleOption

@Composable
fun RadioGroup(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    otherOptionText: (String) -> Unit = {}
) {
    var otherText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = label,
            style = TextStyle(
                color = Color(0xFF049454F),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = { onOptionSelected(option) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF00369A1)
                    )
                )
                Text(
                    text = option,
                    style = TextStyle(
                        color = Color(0xFF049454F),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        if (selectedOption == "Outra") {
            OutlinedTextField(
                value = otherText,
                onValueChange = { newText ->
                    otherText = newText
                    otherOptionText(newText)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                label = { Text("Resposta") },
                colors = OutlinedTextFieldDefaults. colors(
                    focusedBorderColor = Color(0xFF00369A1),
                    focusedLabelColor = Color(0xFF00369A1)
                )
            )
        }
    }
}

@Preview
@Composable
private fun RadioGroupPreview() {

    var selectedCondition by remember { mutableStateOf("") }

    RadioGroup(label = "Marque uma opção",
        options = listOf(
            "Opção 1",
            "Opção 2",
            "Opção 3",
            "Opção 4",
            "Outra"
        ),
        selectedOption = selectedCondition,
        onOptionSelected = { option ->
           selectedCondition = option
        },
        otherOptionText = {}
    )
}
