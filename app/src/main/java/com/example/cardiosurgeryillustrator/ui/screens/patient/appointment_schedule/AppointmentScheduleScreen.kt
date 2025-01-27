package com.example.cardiosurgeryillustrator.ui.screens.patient.appointment_schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.ui.components.patient.appointment_schedule.AppointmentCard
import com.example.cardiosurgeryillustrator.ui.theme.Blue600
import com.example.cardiosurgeryillustrator.ui.theme.Zinc50
import com.example.cardiosurgeryillustrator.ui.theme.Zinc500
import com.example.cardiosurgeryillustrator.ui.view_models.patient.AppointmentScheduleViewModel

@Composable
fun AppointmentScheduleScreen(
    viewModel: AppointmentScheduleViewModel = AppointmentScheduleViewModel(
        LocalContext.current
    ),
    navController: NavController,
    modifier: Modifier
) {
    val appointments by viewModel.appointments.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
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
                text = "Consultas Registradas",
                fontSize = 22.sp,
            )
        }

        if (appointments.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nenhum agendamento encontrado.",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        } else {

            LazyColumn(modifier = Modifier
                .weight(1f)
                .padding(8.dp)) {
                items(appointments) { appointment ->
                    AppointmentCard(
                        appointment,
                        onDelete = {
                            viewModel.deleteAppointment(
                                navController.context,
                                appointment.id
                            )
                        })
                }
            }
        }

        Button(
            onClick = { navController.navigate("new_appointment_screen") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonColors(
                containerColor = Blue600,
                contentColor = Zinc50,
                disabledContentColor = Zinc500,
                disabledContainerColor = Zinc50
            )
        ) {
            Text("Novo Agendamento")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppoinmentSchedulePreview() {
    AppointmentScheduleScreen(navController = rememberNavController(), modifier = Modifier)
}
