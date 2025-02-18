package com.example.cardiosurgeryillustrator.ui.components.patient.appointment_schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardiosurgeryillustrator.models.mock.patient.mockClinics
import com.example.cardiosurgeryillustrator.models.patient.appointment_schedule.Appointment
import com.example.cardiosurgeryillustrator.ui.theme.Blue600
import java.util.UUID

fun getClinicNameById(clinicId: String): String {
    return mockClinics.find { it.id == clinicId }?.name ?: "Clínica desconhecida"
}

@Composable
fun AppointmentCard(appointment: Appointment, onDelete: (String) -> Unit) {

    val clinicName = getClinicNameById(appointment.clinicId)
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Você tem uma consulta marcada",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                color = Blue600
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Data: ${appointment.date}",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Horário: ${appointment.time}",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Clínica: $clinicName",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onSurface
            )

            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Deletar")
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Confirmar Exclusão") },
            text = { Text(text = "Tem certeza que deseja deletar ?") },

            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete(appointment.id)
                        showDialog = false
                    }
                ) {
                    Text(text = "Sim")
                }

            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(text = "Cancelar")
                }

            }
        )
    }
}


@Preview
@Composable
private fun AppointmentCardPReview() {
    AppointmentCard(Appointment(UUID.randomUUID().toString(),"2025-01-30", "14:30", "1"),
        onDelete = { id ->
            // Lógica de exclusão aqui
            println("Excluir agendamento com ID: $id")
        })
}