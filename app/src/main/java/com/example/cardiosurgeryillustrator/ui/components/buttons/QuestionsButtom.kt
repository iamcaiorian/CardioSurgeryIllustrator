package com.example.cardiosurgeryillustrator.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun QuestionsButton(
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(50.dp)
) {
    val insideColor = if (isSelected) Color(0xFFD0E8FF) else Color(0xFFE0E0E0) // Cor de fundo (azul claro se selecionado)
    val borderColor = if (isSelected) Color(0xFF1976D2) else Color(0xFF8A8A8A) // Cor da borda/texto

    Button(
        onClick = onClick,
        shape = shape,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = insideColor, // Cor de fundo
            contentColor = borderColor // Cor do texto
        ),
        border = BorderStroke(2.dp, borderColor) // Borda dinâmica
    ) {
        Text(text = text, color = borderColor)
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionsButtonPreview() {
    QuestionsButton(
        text = "Opção A",
        onClick = { println("Option clicked") },
        isSelected = false
    )
}