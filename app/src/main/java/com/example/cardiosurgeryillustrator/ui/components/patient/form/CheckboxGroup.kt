package com.example.cardiosurgeryillustrator.ui.components.patient.form

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            .padding(vertical = 32.dp, horizontal = 16.dp)
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
                    }
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
                // Mostra o campo de input apenas se "Outra" estiver selecionado
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
//                    decorationBox = { innerTextField ->
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(8.dp)
//                                .background(MaterialTheme.colorScheme.surface)
//                                .border(width = 1.dp, color = Color.Gray),
//                            contentAlignment = Alignment.CenterStart
//                        ) {
//                            innerTextField()
//                        }
//                    }
                )
            }
        }
    }
}
