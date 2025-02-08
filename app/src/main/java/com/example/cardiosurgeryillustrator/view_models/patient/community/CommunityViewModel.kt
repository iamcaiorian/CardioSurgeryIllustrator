package com.example.cardiosurgeryillustrator.view_models.patient.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.mock.patient.CommunityMock
import com.example.cardiosurgeryillustrator.models.patient.community.Comment
import com.example.cardiosurgeryillustrator.models.patient.community.Topic
import com.example.cardiosurgeryillustrator.models.patient.community.User
import com.example.cardiosurgeryillustrator.ui.components.patient.community.filter.CommunityFilterChipView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommunityViewModel : ViewModel() {
    private val _topics = MutableStateFlow(CommunityMock.getMockTopics())
    val topics: StateFlow<List<Topic>> = _topics

    private val _currentUser = MutableStateFlow(CommunityMock.getMockUsers().first())
    val currentUser: StateFlow<User> = _currentUser

    fun toggleSavedTopic(topicId: String, isSaved: Boolean) {
        viewModelScope.launch {
            val updatedUser = _currentUser.value.copy(
                savedTopics = if (isSaved) {
                    _currentUser.value.savedTopics + topicId
                } else {
                    _currentUser.value.savedTopics - topicId
                }
            )
            _currentUser.value = updatedUser
        }
    }

    fun getMessagesForTopic(topicId: String): StateFlow<List<Comment>> {
        val messages = MutableStateFlow<List<Comment>>(emptyList())

        viewModelScope.launch {
            messages.value = CommunityMock.getMockComments().filter { it.topicId == topicId }
        }

        return messages
    }

}
