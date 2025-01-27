package com.example.cardiosurgeryillustrator.ui.view_models.patient

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.patient.appointment_schedule.Appointment
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppointmentScheduleViewModel(context: Context): ViewModel() {
    val appointmentFlow: Flow<List<Appointment>> = DataStoreUtils.readAppointments(context)

    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments

    init {
        viewModelScope.launch {
            appointmentFlow.collect { _appointments.value = it }
        }
    }

    fun saveAppointments(context: Context, appointments: List<Appointment>) {
        viewModelScope.launch {
            _appointments.value = appointments
            DataStoreUtils.saveAppointments(context, appointments)
        }
    }

    fun deleteAppointment(context: Context, appointmentId: String) {
        viewModelScope.launch {
            DataStoreUtils.deleteAppointment(context, appointmentId)
        }
    }
}