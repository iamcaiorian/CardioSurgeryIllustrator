package com.example.cardiosurgeryillustrator.ui.components.buttons

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
import com.example.cardiosurgeryillustrator.ui.theme.Blue200
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc300
import com.example.cardiosurgeryillustrator.ui.theme.Zinc400
import com.example.cardiosurgeryillustrator.ui.theme.Zinc500
import com.example.cardiosurgeryillustrator.ui.theme.Zinc800
import com.example.cardiosurgeryillustrator.ui.theme.Zinc900

@Composable
fun QuestionsButton(
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp)
) {
    val insideColor = if (isSelected) Zinc400 else Zinc300
    val borderColor = if (isSelected) Zinc800 else Color.Transparent
    val textColor = if (isSelected) Zinc900 else Zinc800

    Button(
        onClick = onClick,
        shape = shape,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = insideColor,
            contentColor = borderColor
        ),
        border = BorderStroke(2.dp, borderColor)
    ) {
        Text(text = text, color = textColor, style = Typography.labelLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionsButtonPreview() {
    QuestionsButton(
        text = "Opção A",
        onClick = { println("Option clicked") },
        isSelected = true
    )
}