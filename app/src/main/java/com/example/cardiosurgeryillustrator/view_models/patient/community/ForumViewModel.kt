package com.example.cardiosurgeryillustrator.view_models.patient.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentRequest
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentResponse
import com.example.cardiosurgeryillustrator.models.patient.community.forum.Topic
import com.example.cardiosurgeryillustrator.repository.patient.community.CommentRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForumViewModel(private val repository: ForumRepository, private val commentRepository: CommentRepository) : ViewModel() {
    private val _topics = MutableStateFlow<List<Topic>>(emptyList())
    val topics: StateFlow<List<Topic>> = _topics

    private val _messages = MutableStateFlow<List<CommentResponse>>(emptyList())
    val messages: StateFlow<List<CommentResponse>> = _messages

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
                    commentResponse = response.comments,
                    likes = 10,
                    comments = 1,
                    timestamp = 11,
                )
            }
        }
    }

    fun getAllMessagesForForum(topicId: String) {
        viewModelScope.launch {
            _messages.value = commentRepository.getAllComments()
        }
    }

    fun sendMessageToTopic(topicId: String, userId: String, message: String) {
        viewModelScope.launch {
            getAllMessagesForForum(topicId)
        }
    }
}
