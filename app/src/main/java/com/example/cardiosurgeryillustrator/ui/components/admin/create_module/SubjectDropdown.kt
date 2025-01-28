package com.example.cardiosurgeryillustrator.ui.components.admin.create_module

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cardiosurgeryillustrator.models.mock.mockSubjects
import com.example.cardiosurgeryillustrator.models.student.subject.Subject
import com.example.cardiosurgeryillustrator.ui.theme.Blue600

@Composable
fun SubjectDropdown(
    subjects: List<Subject>,
    selectedSubject: Subject?,
    onSubjectSelected: (Subject) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = Modifier.fillMaxWidth()) {
        TextButton(onClick = { expanded = !expanded }) {
            Text(
                text = selectedSubject?.title ?: "Selecione um assunto",
                style = MaterialTheme.typography.bodyMedium,
                color = Blue600
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            subjects.forEach { subject ->
                DropdownMenuItem(
                    text = {
                        Text(text = subject.title ?: "Nome da clínica não disponível")
                    },
                    onClick = {
                        onSubjectSelected(subject)
                        expanded = false
                    },
                    interactionSource = interactionSource
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ClincDropdownPreview() {
    SubjectDropdown(subjects = mockSubjects, selectedSubject = null, onSubjectSelected = {})
}