package com.example.cardiosurgeryillustrator.view_models.patient.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.mock.patient.diseases
import com.example.cardiosurgeryillustrator.models.patient.home.HeartDisease
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel() {

    // Armazena o índice da doença selecionada
    private val _selectedDiseaseIndex = MutableStateFlow(-1)
    val selectedDiseaseIndex: StateFlow<Int> = _selectedDiseaseIndex

    // A doença selecionada, se existir
    private val _selectedDisease = MutableStateFlow<HeartDisease?>(null)
    val selectedDisease: StateFlow<HeartDisease?> = _selectedDisease

    init {
        viewModelScope.launch {
            // Observar a resposta da pergunta no DataStore
            DataStoreUtils.readQuestion14Response(context).collectLatest { response ->
                response?.let { diseaseName ->
                    // Aqui, a lógica de busca pelo nome
                    val diseaseIndex = getDiseaseIndexByName(diseaseName)
                    Log.d("teste", "teste")
                }
            }
        }
    }

    // Método para encontrar o índice da doença com base no nome
    private fun getDiseaseIndexByName(diseaseName: String) {
        val diseaseNameFromStore = diseaseName.split("=")[1]

// Encontramos a doença correspondente na lista
        val matchingDisease = diseases.find { it.name == diseaseNameFromStore }

// Agora, você pode usar o `matchingDisease` para acessar o item correspondente
        if (matchingDisease != null) {
            println("Doença encontrada: ${matchingDisease.name}")
        } else {
            println("Doença não encontrada.")
        }
    }
}

