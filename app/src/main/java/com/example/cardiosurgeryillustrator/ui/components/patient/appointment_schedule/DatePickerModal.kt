package com.example.cardiosurgeryillustrator.ui.components.patient.appointment_schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.ui.theme.Blue600
import com.example.cardiosurgeryillustrator.ui.theme.Zinc100
import com.example.cardiosurgeryillustrator.ui.theme.Zinc500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Zinc100, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        // Título do modal
        Text(
            text = "Selecione a Data", // Título do modal em português
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }
                ) {
                    Text("Confirmar", color = Blue600)
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancelar", color = Zinc500)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}



@Preview
@Composable
private fun DatePickerModalPReview() {
    var selectedDate by remember { mutableStateOf<Long?>(null) }

    DatePickerModal(
        onDateSelected = { dateMillis ->
            selectedDate = dateMillis
        },
        onDismiss = {}
    )
}