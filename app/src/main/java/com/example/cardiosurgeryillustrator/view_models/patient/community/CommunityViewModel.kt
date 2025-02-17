package com.example.cardiosurgeryillustrator.view_models.patient.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.patient.community.Patient
import com.example.cardiosurgeryillustrator.models.patient.community.forum.Forum
import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumRequest
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class CommunityViewModel(
    private val forumRepository: ForumRepository,
    private val patientRepository: PatientRepository
) :
    ViewModel() {
    private val _forums = MutableStateFlow<List<Forum>>(emptyList())
    val forums: StateFlow<List<Forum>> = _forums

    private val _currentUser = MutableStateFlow<Patient?>(null)

    val patientId: String?
        get() = _currentUser.value?.id

    init {
        loadForums()
    }

    private fun loadForums() {
        viewModelScope.launch {
            val likedForums =
                patientId?.let { patientRepository.getAllForumsLiked(it) } ?: emptyList()
            val savedForums =
                patientId?.let { patientRepository.getAllForumsSaved(it) } ?: emptyList()

            _forums.value = forumRepository.getAllForums().map { response ->
                Forum(
                    id = response.id,
                    userId = response.creatorId,
                    theme = response.theme,
                    title = response.title,
                    likes = response.likesAmount,
                    comments = response.comments.size,
                    timestamp = response.createdAt,
                    commentResponse = response.comments,
                    isLiked = response.id in likedForums,
                    isFavorite = response.id in savedForums
                )
            }
        }
    }

    fun createNewForum(theme: String, title: String) {
        viewModelScope.launch {
            patientId?.let {
                val request = ForumRequest(theme = theme, title = title, creatorId = it)
                val response = forumRepository.createForum(request)

                val newForum = Forum(
                    id = response.id,
                    userId = it,
                    theme = response.theme,
                    title = response.title,
                    likes = response.likesAmount,
                    comments = response.comments.size,
                    timestamp = LocalDateTime.now(),
                    commentResponse = response.comments,
                    isLiked = false,
                    isFavorite = false
                )
                _forums.value = _forums.value + newForum
            }
        }
    }

    fun deleteForum(forumId: String) {
        viewModelScope.launch {
            forumRepository.deleteForumsById(forumId)
            _forums.value = _forums.value.filterNot { it.id == forumId }
        }
    }

    fun likeForum(forumId: String) {
        viewModelScope.launch {
            patientId?.let {
                forumRepository.likeForum(forumId, it)
                _forums.value = _forums.value.map { forum ->
                    if (forum.id == forumId) forum.copy(
                        likes = forum.likes + 1,
                        isLiked = true
                    ) else forum
                }
            }
        }
    }

    fun saveForum(forumId: String) {
        viewModelScope.launch {
            patientId?.let {
                forumRepository.saveForum(forumId, it)
                _forums.value = _forums.value.map { forum ->
                    if (forum.id == forumId) forum.copy(isFavorite = true) else forum
                }
            }
        }
    }

    fun getForumById(forumId: String) {
        viewModelScope.launch {
            val response = forumRepository.getForumById(forumId)
            val isLiked =
                patientId?.let { patientRepository.getAllForumsLiked(it).contains(response.id) }
                    ?: false
            val isFavorite =
                patientId?.let { patientRepository.getAllForumsSaved(it).contains(response.id) }
                    ?: false

            val forum = Forum(
                id = response.id,
                userId = response.creatorId,
                theme = response.theme,
                title = response.title,
                likes = response.likesAmount,
                comments = response.comments.size,
                timestamp = response.createdAt,
                commentResponse = response.comments,
                isLiked = isLiked,
                isFavorite = isFavorite
            )
            _forums.value = _forums.value.filterNot { it.id == forumId } + forum
        }
    }
}