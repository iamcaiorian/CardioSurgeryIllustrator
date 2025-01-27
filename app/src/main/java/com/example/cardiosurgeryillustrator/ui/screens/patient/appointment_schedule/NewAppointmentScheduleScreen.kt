package com.example.cardiosurgeryillustrator.ui.screens.patient.appointment_schedule

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.cardiosurgeryillustrator.models.mock.mockClinics
import com.example.cardiosurgeryillustrator.models.patient.appointment_schedule.Appointment
import com.example.cardiosurgeryillustrator.models.patient.nearby_clinics.Clinic
import com.example.cardiosurgeryillustrator.ui.components.patient.appointment_schedule.ClinicDropdown
import com.example.cardiosurgeryillustrator.ui.components.patient.appointment_schedule.DatePickerModal
import com.example.cardiosurgeryillustrator.ui.components.patient.appointment_schedule.TimePickerModal
import com.example.cardiosurgeryillustrator.ui.screens.patient.notification.AppointmentNotificationWorker
import com.example.cardiosurgeryillustrator.ui.theme.Blue600
import com.example.cardiosurgeryillustrator.ui.theme.Zinc50
import com.example.cardiosurgeryillustrator.ui.theme.Zinc500
import com.example.cardiosurgeryillustrator.ui.view_models.patient.AppointmentScheduleViewModel
import java.text.DateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

@Composable
fun NewAppointmentScheduleScreen(
    navController: NavController,
    clinics: List<Clinic>,
    viewModel: AppointmentScheduleViewModel = AppointmentScheduleViewModel(LocalContext.current),
    modifier: Modifier
) {

    val appointments by viewModel.appointments.collectAsState()

    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var selectedHour by remember { mutableStateOf(0) }
    var selectedMinute by remember { mutableStateOf(0) }
    var selectedClinic by remember { mutableStateOf<Clinic?>(null) }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar"
                )
            }

            Text(
                text = "Registrar uma nova consulta",
                fontSize = 22.sp,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Registre suas consultas no app para manter" +
                    " sua agenda organizada e ser lembrado com antecedência.",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Data do agendamento
        Button(
            onClick = { showDatePicker = true },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonColors(
                containerColor = Blue600,
                contentColor = Zinc50,
                disabledContentColor = Zinc500,
                disabledContainerColor = Zinc50
            ),
            shape = RectangleShape

        ) {
            Text(
                text = selectedDate?.let { date ->
                    val newDate = date + 24 * 60 * 60 * 1000
                    "Data: ${
                        DateFormat.getDateInstance().format(Date(newDate))
                    }"
                } ?: "Escolher Data"
            )
        }

        if (showDatePicker) {
            DatePickerModal(
                onDateSelected = { date ->
                    selectedDate = date
                    showDatePicker = false
                },
                onDismiss = { showDatePicker = false }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hora do agendamento
        Button(
            onClick = { showTimePicker = true },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonColors(
                containerColor = Blue600,
                contentColor = Zinc50,
                disabledContentColor = Zinc500,
                disabledContainerColor = Zinc50
            ),
            shape = RectangleShape
        ) {
            Text(text = "Escolher Hora: $selectedHour:$selectedMinute")
        }

        if (showTimePicker) {
            TimePickerModal(
                onTimeSelected = { hour, minute ->
                    selectedHour = hour
                    selectedMinute = minute
                    showTimePicker = false
                },
                onDismiss = { showTimePicker = false }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Seletor de Clínica
        Text("Escolher Clínica")
        ClinicDropdown(
            clinics = clinics,
            selectedClinic = selectedClinic,
            onClinicSelected = { selectedClinic = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            // Botão de Salvar
            Button(
                onClick = {
                    selectedClinic?.let {
                        selectedDate?.let { date ->
                            val newDate = date + 24 * 60 * 60 * 1000
                            val formattedDate = DateFormat.getDateInstance().format(Date(newDate))
                            val formattedTime =
                                String.format("%02d:%02d", selectedHour, selectedMinute)
                            val appointment = Appointment(
                                date = formattedDate,
                                time = formattedTime,
                                clinicId = it.id
                            )
                            viewModel.saveAppointments(
                                navController.context,
                                appointments + appointment
                            )
                            // Calculando o atraso para a notificação (1 dia antes do agendamento)
                            val oneDayBeforeMillis = date - TimeUnit.DAYS.toMillis(1) // Um dia antes da data do agendamento

                            // Verificando se a notificação precisa ser agendada
                            val delayMillis = oneDayBeforeMillis - System.currentTimeMillis()

                            // Agendar a notificação se o atraso for positivo
                            if (delayMillis > 0) {
                                val inputData = workDataOf("appointment_date" to oneDayBeforeMillis)

                                val notificationRequest = OneTimeWorkRequestBuilder<AppointmentNotificationWorker>()
                                    .setInitialDelay(delayMillis, TimeUnit.MILLISECONDS) // Ajusta o atraso corretamente
                                    .setInputData(inputData)
                                    .build()

                                // Enfileira a notificação
                                WorkManager.getInstance(navController.context).enqueue(notificationRequest)
                                Log.d("Enfileirando notificação para", formattedDate)
                            } else {
                                Log.d("Notificação não agendada", "A data de agendamento já passou ou é para o mesmo dia.")
                            }
                        }
                    }
                    navController.popBackStack()
                },
                enabled = selectedClinic != null && selectedDate != null,
                colors = ButtonColors(
                    containerColor = Blue600,
                    contentColor = Zinc50,
                    disabledContentColor = Zinc50,
                    disabledContainerColor = Zinc500
                )
            ) {
                Text("Salvar Consulta")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppointmentScheduleScreenPreview() {
    val clinics = mockClinics

    NewAppointmentScheduleScreen(
        navController = rememberNavController(),
        clinics = clinics,
        modifier = Modifier
    )
}
