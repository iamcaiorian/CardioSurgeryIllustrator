package com.example.cardiosurgeryillustrator.models.patient.community.forum

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.cardiosurgeryillustrator.models.patient.community.comment.CommentResponse

data class Forum(
    val id: String,
    val userId: String,
    val theme: String,
    val title: String,
    val commentResponse: List<CommentResponse>,
    var likes: Int,
    var comments: Int,
    val timestamp: String,
    var isLiked: MutableState<Boolean> = mutableStateOf(false),
    var isFavorite: MutableState<Boolean> = mutableStateOf(false),
    var commentsAmount: MutableState<Int> = mutableIntStateOf(commentResponse.size)
)
