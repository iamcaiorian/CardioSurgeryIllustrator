package com.example.cardiosurgeryillustrator.view_models.patient.community

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentRequest
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentResponse
import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumResponse
import com.example.cardiosurgeryillustrator.models.patient.patient.PatientResponse
import com.example.cardiosurgeryillustrator.repository.patient.community.CommentRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ForumViewModel(
    private val forumRepository: ForumRepository,
    private val commentRepository: CommentRepository,
    private val patientRepository: PatientRepository,
    private val context: Context
) : ViewModel() {

    private val _patientId = MutableStateFlow<String?>(null)
    val patientId: StateFlow<String?> = _patientId

    private val _patientResponse = MutableStateFlow<PatientResponse?>(null)
    val patientResponse: StateFlow<PatientResponse?> = _patientResponse

    private val _forum = MutableStateFlow<ForumResponse?>(null)
    val forum: StateFlow<ForumResponse?> = _forum

    private val _messages = MutableStateFlow<List<CommentResponse>>(emptyList())
    val messages: StateFlow<List<CommentResponse>> = _messages

    private val _isLiked = MutableStateFlow(false)
    val isLiked: StateFlow<Boolean> = _isLiked

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    init {
        loadPatientId()
    }

    private fun loadPatientId() {
        viewModelScope.launch {
            val id = DataStoreUtils.readPatientUUID(context).first()
            _patientId.value = id

            id?.let { loadPatient(it) }
        }
    }

    private fun loadPatient(patientId: String) {
        viewModelScope.launch {
            try {
                val patient = patientRepository.getPatientById(patientId)
                _patientResponse.value = patient
            } catch (e: Exception) {
                Log.e("ForumViewModel", "Erro ao carregar dados do paciente", e)
            }
        }
    }

    fun loadForum(forumId: String) {
        viewModelScope.launch {
            try {
                val forumResponse = forumRepository.getForumById(forumId)
                _forum.value = forumResponse
                _messages.value = forumResponse.comments
            } catch (e: Exception) {
                Log.e("ForumViewModel", "Erro ao carregar fórum", e)
            }
        }
    }

    fun sendMessageToForum(forumId: String, message: String) {
        viewModelScope.launch {
            patientId.value?.let { id ->
                val request = CommentRequest(forumId = forumId, patientId = id, content = message)
                try {
                    val newComment = commentRepository.createComment(request)

                    val updatedPatient = patientRepository.getPatientById(id)
                    _patientResponse.value = updatedPatient

                    _messages.value += newComment

                } catch (e: Exception) {
                    Log.e("ForumViewModel", "Erro ao enviar mensagem", e)
                }
            }
        }
    }


    fun likeForum(forumId: String) {
        viewModelScope.launch {
            val patientId = _patientId.value
            if (patientId != null) {
                try {
                    forumRepository.likeForum(forumId, patientId)
                    _isLiked.value = true
                } catch (e: Exception) {
                    Log.e("ForumViewModel", "Erro ao curtir o fórum", e)
                }
            }
        }
    }

    fun saveForum(forumId: String) {
        viewModelScope.launch {
            val patientId = _patientId.value
            if (patientId != null) {
                try {
                    forumRepository.saveForum(forumId, patientId)
                    _isFavorite.value = true
                } catch (e: Exception) {
                    Log.e("ForumViewModel", "Erro ao salvar o fórum", e)
                }
            }
        }
    }
}
