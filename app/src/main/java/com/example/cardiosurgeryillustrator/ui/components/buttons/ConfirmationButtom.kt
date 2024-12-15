package com.example.cardiosurgeryillustrator.ui.components.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmationButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2196F3),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(
            topStart = 24.dp,
            bottomStart = 24.dp,
            topEnd = 24.dp,
            bottomEnd = 24.dp
        ),
        modifier = modifier
            .padding(8.dp)
            .height(60.dp)
            .width(160.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(end = 8.dp)
        )
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun ConfirmationButtonPreview() {
    ConfirmationButton(
        text = "Confirmar!",
        onClick = {})
}