package com.example.cardiosurgeryillustrator.view_models.patient.form

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.patient.patient.PatientRequest
import com.example.cardiosurgeryillustrator.models.patient.patient.QuestionAndAnswer
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.UUID

class CardioFormViewModel(
    context: Context,
    private val patientRepository: PatientRepository
) : ViewModel() {

    private val _hasAnsweredForm = MutableStateFlow(false)
    val hasAnsweredForm: StateFlow<Boolean> = _hasAnsweredForm

    private val _userDiseaseIndex = MutableStateFlow<Int?>(null)
    val userDiseaseIndex: StateFlow<Int?> = _userDiseaseIndex

    private val appContext = context.applicationContext

    init {
        viewModelScope.launch {
            val storedUUID = DataStoreUtils.readPatientUUID(appContext).first()
            _hasAnsweredForm.value = storedUUID != null

            val diseaseIndex = DataStoreUtils.readUserDiseaseIndex(appContext).first()
            _userDiseaseIndex.value = diseaseIndex
        }
    }

    fun saveFormResponse(userId: String, questionsAndAnswers: List<QuestionAndAnswer>) {
        viewModelScope.launch {
            try {
                val patientRequest = PatientRequest(UUID.fromString(userId), questionsAndAnswers)
                val patientResponse = patientRepository.createPatient(patientRequest)

                DataStoreUtils.savePatientUUID(appContext, patientResponse.userId.toString())
                _hasAnsweredForm.value = true

                Log.d("CardioFormViewModel", "Paciente criado com sucesso: $patientResponse")
            } catch (e: Exception) {
                Log.e("CardioFormViewModel", "Erro ao criar paciente: ${e.message}")
            }
        }
    }

    fun saveQuestion14Response(response: String) {
        viewModelScope.launch {
            DataStoreUtils.saveQuestion14Response(appContext, response)
        }
    }

    // Salvar o IMC no DataStore
    fun saveIMC(imc: String) {
        viewModelScope.launch {
            DataStoreUtils.saveIMC(appContext, imc)
        }
    }

    // Obter informações do paciente do servidor
    fun getPatientById(userId: String) {
        viewModelScope.launch {
            try {
                val patientResponse = patientRepository.getPatientById(userId)
                Log.d("CardioFormViewModel", "Paciente encontrado: $patientResponse")
            } catch (e: Exception) {
                Log.e("CardioFormViewModel", "Erro ao buscar paciente: ${e.message}")
            }
        }
    }

    // Obter formulário preenchido por um paciente
    fun getPatientForm(userId: String) {
        viewModelScope.launch {
            try {
                val formResponse = patientRepository.getPatientForm(userId)
                Log.d("CardioFormViewModel", "Formulário do paciente: $formResponse")
            } catch (e: Exception) {
                Log.e("CardioFormViewModel", "Erro ao buscar formulário: ${e.message}")
            }
        }
    }

    fun saveUserDiseaseIndex(index: Int) {
        viewModelScope.launch {
            DataStoreUtils.saveUserDiseaseIndex(appContext, index)
            _userDiseaseIndex.value = index
        }
    }

    fun getUserDiseaseIndex() {
        viewModelScope.launch {
            _userDiseaseIndex.value = DataStoreUtils.readUserDiseaseIndex(appContext).first()
        }
    }

}