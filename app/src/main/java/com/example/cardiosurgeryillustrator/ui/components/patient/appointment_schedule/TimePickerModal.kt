package com.example.cardiosurgeryillustrator.ui.components.patient.appointment_schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
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
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerModal(
    onTimeSelected: (Int, Int) -> Unit,
    onDismiss: () -> Unit
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Zinc100, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        // Time Picker
        TimePicker(state = timePickerState)

        Spacer(modifier = Modifier.height(16.dp))

        // Botões para Confirmar ou Cancelar
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = Zinc500)
            }
            Spacer(modifier = Modifier.width(8.dp))
            TextButton(onClick = {
                onTimeSelected(
                    timePickerState.hour,
                    timePickerState.minute
                )
                onDismiss()
            }) {
                Text("Confirmar", color = Blue600)
            }
        }
    }
}


@Preview
@Composable
private fun TimePickerModalPreview() {
    var selectedHour by remember { mutableStateOf(10) }
    var selectedMinute by remember { mutableStateOf(30) }

    TimePickerModal(
        onTimeSelected = { hour, minute ->
            selectedHour = hour
            selectedMinute = minute
        },
        onDismiss = {}
    )
}