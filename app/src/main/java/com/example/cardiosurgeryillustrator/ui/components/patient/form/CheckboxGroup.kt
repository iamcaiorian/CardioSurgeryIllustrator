package com.example.cardiosurgeryillustrator.ui.components.patient.form

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun CheckboxGroup(
    label: String,
    options: List<String>,
    selectedOptions: List<String>,
    onOptionToggled: (String) -> Unit,
    onOtherTextChanged: (String) -> Unit = {} // Callback para capturar o texto de "Outra"
) {
    var otherText by remember { mutableStateOf("") } // Estado local para armazenar o texto de "Outra"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp)
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selectedOptions.contains(option),
                    onCheckedChange = { isChecked ->
                        if (option == "Outra" && !isChecked) {
                            // Limpa o texto de "Outra" se for desmarcado
                            otherText = ""
                            onOtherTextChanged("")
                        }
                        onOptionToggled(option)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF00369A1)
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
            if (option == "Outra" && selectedOptions.contains(option)) {
                OutlinedTextField(
                    value = otherText,
                    onValueChange = { newText ->
                        otherText = newText
                        onOtherTextChanged(newText)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    label = { Text("Resposta") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF00369A1),
                        focusedLabelColor = Color(0xFF00369A1)
                    )
                )
            }
        }
    }
}


@Preview
@Composable
private fun CheckBoxGroupPreview() {

    var selectedOptions by remember { mutableStateOf(listOf<String>()) }

    CheckboxGroup(
        label = "Marque todas as opções que quiser.",
        options = listOf(
            "Opção 1",
            "Opção 2",
            "Opção 3",
            "Opção 4",
            "Outra"
        ),
        selectedOptions = selectedOptions,
        onOptionToggled = { option ->
            toggleOption(option, selectedOptions) { newSelectedOptions ->
                selectedOptions = newSelectedOptions
            }
        },
        onOtherTextChanged = {}
    )
}
