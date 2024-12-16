package com.example.cardiosurgeryillustrator.ui.components.input

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.ui.theme.Typography
import com.example.cardiosurgeryillustrator.ui.theme.Zinc400
import com.example.cardiosurgeryillustrator.ui.theme.Zinc900

@Composable
fun TextInput(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    TextField(
        modifier = modifier
            .height(48.dp),
        shape = RoundedCornerShape(100.dp),
        value = text,
        onValueChange = { text = it },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Zinc400.copy(alpha = 0.2f)
        ),
        placeholder = { Text("Escreva uma mensagem...", style = Typography.bodyMedium, color = Zinc900)  },
    )
}

@Preview
@Composable
private fun TextInputPreview() {
    TextInput()
}