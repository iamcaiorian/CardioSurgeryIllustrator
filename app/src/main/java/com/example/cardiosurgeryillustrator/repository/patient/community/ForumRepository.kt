package com.example.cardiosurgeryillustrator.repository.patient.community

import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.patient.community.Message
import com.example.cardiosurgeryillustrator.models.patient.community.topic.TopicRequest
import com.example.cardiosurgeryillustrator.models.patient.community.topic.TopicResponse

class ForumRepository {
    suspend fun createForum(topicRequest: TopicRequest): TopicResponse {
        return RetrofitInstance.forumService.createForum(topicRequest)
    }

    suspend fun getAllForums(): List<TopicResponse> {
        return RetrofitInstance.forumService.getAllForums()
    }

    suspend fun deleteForumsById(topicId: String) {
        RetrofitInstance.forumService.deleteForumById(topicId)
    }

    suspend fun getAllMessagesForForum(forumId: String): List<Message> {
        return RetrofitInstance.forumService.getAllMessagesForForum(forumId)
    }

    suspend fun sendMessageToForum(forumId: String, userId: String, content: String) {
        val message = Message(userId, content)
        RetrofitInstance.forumService.postNewMessage(forumId, message)
    }
}
