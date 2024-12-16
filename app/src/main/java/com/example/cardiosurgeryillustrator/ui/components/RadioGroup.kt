package com.example.cardiosurgeryillustrator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            .padding(vertical = 16.dp, horizontal = 8.dp)
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
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = { onOptionSelected(option) }
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
//                decorationBox = { innerTextField ->
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp)
//                            .background(MaterialTheme.colorScheme.surface)
//                            .border(width = 1.dp, color = Color.Gray),
//                        contentAlignment = Alignment.CenterStart
//                    ) {
//                        innerTextField()
//                    }
//                }
            )
        }
    }
}
