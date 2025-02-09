package com.example.cardiosurgeryillustrator.ui.components.patient.community.forum

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.ui.components.buttons.StandardButton
import com.example.cardiosurgeryillustrator.ui.components.input.StandardTextField

@Composable
fun NewForumDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    var theme by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Criar Novo Fórum",
                style = androidx.compose.material3.MaterialTheme.typography.displaySmall
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StandardTextField(
                    label = "Tema",
                    value = theme,
                    onValueChange = { theme = it },
                    modifier = Modifier.fillMaxWidth()
                )
                StandardTextField(
                    label = "Título",
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            StandardButton(
                text = "Criar",
                onClick = {
                    if (theme.isNotEmpty() && title.isNotEmpty()) {
                        onConfirm(theme, title)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
        dismissButton = {
            StandardButton(
                text = "Cancelar",
                onClick = { onDismiss() },
                color = androidx.compose.ui.graphics.Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}
