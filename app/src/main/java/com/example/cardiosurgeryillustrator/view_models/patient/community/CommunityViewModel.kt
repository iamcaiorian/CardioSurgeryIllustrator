package com.example.cardiosurgeryillustrator.view_models.patient.community

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiosurgeryillustrator.models.patient.community.forum.Forum
import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumRequest
import com.example.cardiosurgeryillustrator.repository.patient.community.ForumRepository
import com.example.cardiosurgeryillustrator.repository.patient.community.PatientRepository
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import com.example.cardiosurgeryillustrator.utils.makeForumEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class CommunityViewModel(
    private val forumRepository: ForumRepository,
    private val patientRepository: PatientRepository,
    private val context: Context
) :
    ViewModel() {
    private val _forums = MutableStateFlow<List<Forum>>(emptyList())
    val forums: StateFlow<List<Forum>> = _forums

    private var patientId: String? = null

    init {
        loadPatientId()
    }

    private fun loadPatientId() {
        viewModelScope.launch {
            patientId = DataStoreUtils.readPatientUUID(context).first()
            loadForums()
        }
    }

    private fun loadForums() {
        viewModelScope.launch {
            try {
                val likedForums = patientRepository.getAllForumsLiked(patientId.toString())
                val savedForums = patientRepository.getAllForumsSaved(patientId.toString()
                )

                _forums.value = forumRepository.getAllForums().map { response ->
                    makeForumEntity(
                        forumResponse = response,
                        userId = "",
                        isLiked = response.id in likedForums,
                        isFavorite = response.id in savedForums
                    )
                }
            } catch (e: Exception) {
                Log.e("ForumViewModel", "Erro ao carregar os fóruns", e)
            }
        }
    }


    fun createNewForum(theme: String, title: String) {
        viewModelScope.launch {
            patientId?.let {
                val request = ForumRequest(theme = theme, title = title, creatorId = it)
                val response = forumRepository.createForum(request)

                val newForum = makeForumEntity(
                    response ?: throw IllegalArgumentException("ForumResponse não pode ser nulo"),
                    patientId ?: throw IllegalArgumentException("patientId não pode ser nulo"),
                    false,
                    false
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
            patientId?.let { patientId ->
                forumRepository.likeForum(forumId, patientId)
                _forums.value = _forums.value.map { forum ->
                    if (forum.id == forumId) {
                        forum.copy(
                            likes = forum.likes + 1,
                            isLiked = mutableStateOf(true) // Atualizando corretamente como MutableState
                        )
                    } else forum
                }
            }
        }
    }

    fun saveForum(forumId: String) {
        viewModelScope.launch {
            patientId?.let { patientId ->
                forumRepository.saveForum(forumId, patientId)
                _forums.value = _forums.value.map { forum ->
                    if (forum.id == forumId) {
                        forum.copy(
                            isFavorite = mutableStateOf(true) // Atualizando corretamente como MutableState
                        )
                    } else forum
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

            val forum = makeForumEntity(
                response ?: throw IllegalArgumentException("ForumResponse não pode ser nulo"),
                patientId ?: throw IllegalArgumentException("patientId não pode ser nulo"),
                isLiked,
                isFavorite
            )
            _forums.value = _forums.value.filterNot { it.id == forumId } + forum
        }
    }
}