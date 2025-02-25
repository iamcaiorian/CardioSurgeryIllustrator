package com.example.cardiosurgeryillustrator.view_models.patient.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class PatientViewModel(private val patientRepository: PatientRepository) : ViewModel() {
    private val _likedForums = MutableStateFlow<List<String>>(emptyList())
    val likedForums: StateFlow<List<String>> = _likedForums

    private val _savedForums = MutableStateFlow<List<String>>(emptyList())
    val savedForums: StateFlow<List<String>> = _savedForums

//    init {
//        fetchLikedAndSavedForums()
//    }
//
//    private fun fetchLikedAndSavedForums(patientId: UUID) {
//        viewModelScope.launch {
//            _likedForums.value = patientRepository.getAllForumsLiked(patientId)
//            _savedForums.value = patientRepository.getAllForumsSaved(patientId)
//        }
//    }
//
//    fun toggleSavedTopic(topicId: String, isSaved: Boolean) {
//        viewModelScope.launch {
//            _savedForums.value = if (isSaved) {
//                _savedForums.value + topicId
//            } else {
//                _savedForums.value - topicId
//            }
//        }
//    }
}
