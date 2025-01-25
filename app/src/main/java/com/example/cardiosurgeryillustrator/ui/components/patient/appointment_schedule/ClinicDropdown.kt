package com.example.cardiosurgeryillustrator.ui.components.patient.appointment_schedule

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
import com.example.cardiosurgeryillustrator.models.mock.mockClinics
import com.example.cardiosurgeryillustrator.models.patient.nearby_clinics.Clinic
import com.example.cardiosurgeryillustrator.ui.theme.Blue600

@Composable
fun ClinicDropdown(
    clinics: List<Clinic>,
    selectedClinic: Clinic?,
    onClinicSelected: (Clinic) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = Modifier.fillMaxWidth()) {
        TextButton(onClick = { expanded = !expanded }) {
            Text(
                text = selectedClinic?.name ?: "Selecione uma Clínica",
                style = MaterialTheme.typography.bodyMedium,
                color = Blue600
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            clinics.forEach { clinic ->
                DropdownMenuItem(
                    text = {
                        Text(text = clinic.name ?: "Nome da clínica não disponível")
                    },
                    onClick = {
                        onClinicSelected(clinic)
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
    ClinicDropdown(clinics = mockClinics, selectedClinic = null, onClinicSelected = {})
}

