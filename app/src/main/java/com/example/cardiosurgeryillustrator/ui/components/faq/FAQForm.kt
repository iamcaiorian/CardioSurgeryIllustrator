package com.example.cardiosurgeryillustrator.ui.components.faq

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.screens.admin.faq.FaqItem

@Composable
fun FaqForm(
    faq: FaqItem?,
    onSave: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    var question by remember { mutableStateOf(faq?.question ?: "") }
    var answer by remember { mutableStateOf(faq?.answer ?: "") }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(text = if (faq == null) "Adicionar FAQ" else "Editar FAQ") },
        text = {
            Column {
                TextField(
                    value = question,
                    onValueChange = { question = it },
                    label = { Text("Pergunta") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = answer,
                    onValueChange = { answer = it },
                    label = { Text("Resposta") }
                )
            }
        },
        confirmButton = {
            StandardButton(
                onClick = { onSave(question, answer) },
                text = "Salvar"
            )
        },
        dismissButton = {
            StandardButton(
                onClick = onCancel,
                text = "Cancelar",
                color = Color.Red
            )
        }
    )
}