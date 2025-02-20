package com.example.cardiosurgeryillustrator.ui.components.patient.assistent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.ui.theme.Blue700
import com.example.cardiosurgeryillustrator.ui.theme.Zinc300
import kotlinx.coroutines.delay

@Composable
fun AssistantTypingBubble() {
    var dotCount by remember { mutableStateOf(1) }
    val dots = remember { listOf(".", "..", "...") }

    // Atualiza a animação a cada 500ms para simular digitação
    LaunchedEffect(Unit) {
        while (true) {
            delay(275)
            dotCount = (dotCount + 1) % dots.size
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Zinc300,
                    shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 2.dp)
                )
                .padding(12.dp)
                .widthIn(max = 100.dp)
        ) {
            Text(
                text = dots[dotCount], // Alterna entre ".", "..", "..."
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}
