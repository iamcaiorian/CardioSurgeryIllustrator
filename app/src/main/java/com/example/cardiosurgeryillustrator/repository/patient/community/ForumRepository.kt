package com.example.cardiosurgeryillustrator.repository.patient.community

import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumRequest
import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumResponse

class ForumRepository {
    suspend fun createForum(forumRequest: ForumRequest): ForumResponse {
        return RetrofitInstance.forumService.createForum(forumRequest)
    }

    suspend fun getAllForums(): List<ForumResponse> {
        return RetrofitInstance.forumService.getAllForums()
    }

    suspend fun getForumById(forumId: String): ForumResponse {
        return RetrofitInstance.forumService.getForumById(forumId)
    }

    // Curtir um fórum
    suspend fun likeForum(forumId: String, patientId: String) {
        RetrofitInstance.forumService.likeForum(forumId, patientId)
    }

    // Salvar um fórum
    suspend fun saveForum(forumId: String, patientId: String) {
        RetrofitInstance.forumService.saveForum(forumId, patientId)
    }

    suspend fun deleteForumsById(topicId: String) {
        RetrofitInstance.forumService.deleteForumById(topicId)
    }
}
