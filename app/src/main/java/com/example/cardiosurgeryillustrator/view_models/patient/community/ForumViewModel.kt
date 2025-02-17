package com.example.cardiosurgeryillustrator.view_models.patient.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.patient.community.Patient
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentRequest
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentResponse
import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumResponse
import com.example.cardiosurgeryillustrator.repository.patient.community.CommentRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForumViewModel(
    private val forumRepository: ForumRepository,
    private val commentRepository: CommentRepository,
    private val patientRepository: PatientRepository
) : ViewModel() {
    private val _currentUser = MutableStateFlow<Patient?>(null)

    val patientId: String?
        get() = _currentUser.value?.id

    private val _forum = MutableStateFlow<ForumResponse?>(null)
    val forum: StateFlow<ForumResponse?> = _forum

    private val _messages = MutableStateFlow<List<CommentResponse>>(emptyList())
    val messages: StateFlow<List<CommentResponse>> = _messages

    private val _isLiked = MutableStateFlow(false)
    val isLiked: StateFlow<Boolean> = _isLiked

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun loadForum(forumId: String, userId: String) {
        viewModelScope.launch {
            val forumResponse = forumRepository.getForumById(forumId)
            val likedForums = patientRepository.getAllForumsLiked(userId)
            val savedForums = patientRepository.getAllForumsSaved(userId)

            _forum.value = forumResponse
            _isLiked.value = forumId in likedForums
            _isFavorite.value = forumId in savedForums

            getAllMessagesForForum(forumId)
        }
    }

    fun getAllMessagesForForum(forumId: String) {
        viewModelScope.launch {
            _messages.value = commentRepository.getAllComments().filter { it.id == forumId }
        }
    }

    fun sendMessageToForum(forumId: String, message: String) {
        viewModelScope.launch {
            val request =
                CommentRequest(forumId = forumId, patientId = patientId, content = message)
            commentRepository.createComment(request)
            getAllMessagesForForum(forumId)
        }
    }

    fun likeForum(forumId: String, userId: String) {
        viewModelScope.launch {
            forumRepository.likeForum(forumId, userId)
            _isLiked.value = true
        }
    }

    fun saveForum(forumId: String, userId: String) {
        viewModelScope.launch {
            forumRepository.saveForum(forumId, userId)
            _isFavorite.value = true
        }
    }
}