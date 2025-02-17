package com.example.cardiosurgeryillustrator.view_models.patient.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.patient.community.forum.Topic
import com.example.cardiosurgeryillustrator.models.patient.community.Patient
import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumRequest
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommunityViewModel(private val repository: ForumRepository) : ViewModel() {
    private val _topics = MutableStateFlow<List<Topic>>(emptyList())
    val topics: StateFlow<List<Topic>> = _topics

    private val _currentUser = MutableStateFlow<Patient?>(null)
    val currentUser: StateFlow<Patient?> = _currentUser

    init {
        loadForums()
    }

    private fun loadForums() {
        viewModelScope.launch {
            _topics.value = repository.getAllForums().map { response ->
                Topic(
                    id = response.id,
                    userId = response.id,
                    theme = response.theme,
                    title = response.title,
                    likes = response.likesAmount,
                    comments = 1,
                    timestamp = 1,
                    commentResponse = response.comments
                )
            }
        }
    }

    fun createNewForum(theme: String, title: String, userId: String) {
        viewModelScope.launch {
            val request = ForumRequest(theme = theme, title = title, creatorId = userId)
            val response = repository.createForum(request)

            val newTopic = Topic(
                id = response.id,
                userId = response.id,
                theme = response.theme,
                title = response.title,
                likes = response.likesAmount,
                comments = 1,
                timestamp = 1,
                commentResponse = response.comments
            )
            _topics.value = _topics.value + newTopic
        }
    }

    fun deleteForum(topicId: String) {
        viewModelScope.launch {
            repository.deleteForumsById(topicId)
            _topics.value = _topics.value.filterNot { it.id == topicId }
        }
    }
}