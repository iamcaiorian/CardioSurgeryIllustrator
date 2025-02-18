package com.example.cardiosurgeryillustrator.view_models.patient.form

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.UUID

class CardioFormViewModel(context: Context) : ViewModel() {
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

    fun saveFormResponse() {
        viewModelScope.launch {
            val newUUID = UUID.randomUUID().toString()
            DataStoreUtils.savePatientUUID(appContext, newUUID)
            _hasAnsweredForm.value = true
        }
    }

    fun saveQuestion14Response(response: String) {
        viewModelScope.launch {
            DataStoreUtils.saveQuestion14Response(appContext, response)
        }
    }

    fun saveIMC(imc: String) {
        viewModelScope.launch {
            DataStoreUtils.saveIMC(appContext, imc)
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
