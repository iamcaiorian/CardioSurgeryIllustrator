package com.example.cardiosurgeryillustrator.ui.components.patient.message_bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.cardiosurgeryillustrator.ui.theme.Blue700

@Composable
fun MessageBottomBar(
    modifier: Modifier = Modifier,
    messageText: TextFieldValue,
    onMessageTextChange: (TextFieldValue) -> Unit,
    onSendClick: () -> Unit,
    placeholder: String = ""
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = messageText,
            onValueChange = onMessageTextChange,
            modifier = Modifier
                .weight(1f)
                .background(
                    Color.White,
                    RoundedCornerShape(20.dp)
                )
                .border(
                    width = 1.dp,
                    color = Blue700,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
            singleLine = true,
            textStyle = TextStyle.Default.copy(color = Color.Black),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (messageText.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TextStyle.Default.copy(
                                color = Color.Gray,
                                textAlign = TextAlign.Start
                            )
                        )
                    }
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = onSendClick
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "Enviar",
                tint = Blue700
            )
        }
    }
}

@Preview
@Composable
fun MessageBottomBarPreview() {
    var messageText by remember { mutableStateOf(TextFieldValue()) }
    MessageBottomBar(
        messageText = messageText,
        onMessageTextChange = { messageText = it },
        onSendClick = { println("Mensagem enviada: ${messageText.text}") },
        placeholder = "Digite sua mensagem..."
    )
}
