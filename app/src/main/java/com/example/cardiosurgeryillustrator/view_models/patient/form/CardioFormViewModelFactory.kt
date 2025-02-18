package com.example.cardiosurgeryillustrator.view_models.patient.form

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository

class CardioFormViewModelFactory(
    private val context: Context,
    private val patientRepository: PatientRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardioFormViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CardioFormViewModel(context, patientRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
