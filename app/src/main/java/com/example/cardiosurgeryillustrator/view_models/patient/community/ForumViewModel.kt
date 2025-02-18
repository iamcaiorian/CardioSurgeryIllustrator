package com.example.cardiosurgeryillustrator.view_models.patient.community

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.patient.community.Patient
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentRequest
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentResponse
import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumResponse
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
            _patientId.value = DataStoreUtils.readPatientUUID(context).first()
        }
    }

    fun loadForum(forumId: String) {
        viewModelScope.launch {
            val forumResponse = forumRepository.getForumById(forumId)
            _forum.value = forumResponse
            _messages.value = forumResponse.comments
        }
    }


    fun sendMessageToForum(forumId: String, message: String) {
        viewModelScope.launch {
            patientId?.let { id ->
                val request = CommentRequest(forumId = forumId, patientId = patientId.value, content = message)
                commentRepository.createComment(request)
                loadForum(forumId)
            }
        }
    }

    fun likeForum(forumId: String) {
        viewModelScope.launch {
            patientId.value?.let { id ->
                forumRepository.likeForum(forumId, id)
                _isLiked.value = true
            }
        }
    }

    fun saveForum(forumId: String) {
        viewModelScope.launch {
            patientId.value?.let { id ->
                forumRepository.saveForum(forumId, id)
                _isFavorite.value = true
            }
        }
    }
}
