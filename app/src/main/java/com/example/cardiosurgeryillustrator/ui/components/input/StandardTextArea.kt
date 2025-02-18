package com.example.cardiosurgeryillustrator.ui.components.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.ui.theme.Blue700

@Composable
fun StandardTextArea(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Blue700,
            focusedLabelColor = Blue700
        ),
        visualTransformation = VisualTransformation.None,
        maxLines = 10
    )
}
