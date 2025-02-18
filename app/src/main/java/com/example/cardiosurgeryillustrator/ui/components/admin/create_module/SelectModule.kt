package com.example.cardiosurgeryillustrator.ui.components.admin.create_module

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cardiosurgeryillustrator.models.student.module.ModuleResponse
import com.example.cardiosurgeryillustrator.ui.theme.Blue600

@Composable
fun SelectModule(
    modules: List<ModuleResponse>,
    selectedModule: ModuleResponse?,
    onModuleSelected: (ModuleResponse) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        TextButton(onClick = { expanded = !expanded }) {
            Text(
                text = selectedModule?.title ?: "Selecione um módulo",
                style = MaterialTheme.typography.bodyMedium,
                color = Blue600
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            modules.forEach { module ->
                DropdownMenuItem(
                    text = {
                        Text(text = module.title ?: "Nome do módulo não disponível")
                    },
                    onClick = {
                        onModuleSelected(module)
                        expanded = false
                    }
                )
            }
        }
    }
}
