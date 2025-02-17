package com.example.cardiosurgeryillustrator.view_models.patient.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentRequest
import com.example.cardiosurgeryillustrator.models.patient.community.forum.Topic
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForumViewModel(private val repository: ForumRepository) : ViewModel() {
    private val _topics = MutableStateFlow<List<Topic>>(emptyList())
    val topics: StateFlow<List<Topic>> = _topics

    private val _messages = MutableStateFlow<List<CommentRequest>>(emptyList())
    val messages: StateFlow<List<CommentRequest>> = _messages

    init {
        loadForums()
    }

    private fun loadForums() {
        viewModelScope.launch {
            _topics.value = repository.getAllForums().map { response ->
                Topic(
                    id = response.id,
                    userId = response.userId,
                    theme = response.theme,
                    title = response.title,
                    commentRequests = response.messages,
                    likes = response.likes,
                    comments = response.comments,
                    timestamp = response.timestamp
                )
            }
        }
    }

    fun getAllMessagesForForum(topicId: String) {
        viewModelScope.launch {
            _messages.value = repository.getAllMessagesForForum(topicId)
        }
    }

    fun sendMessageToTopic(topicId: String, userId: String, message: String) {
        viewModelScope.launch {
            repository.sendMessageToForum(topicId, userId, message)
            getAllMessagesForForum(topicId)
        }
    }
}
