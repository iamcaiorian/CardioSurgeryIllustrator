package com.example.cardiosurgeryillustrator.utils

import androidx.compose.runtime.mutableStateOf
import com.example.cardiosurgeryillustrator.models.patient.community.forum.Forum
import com.example.cardiosurgeryillustrator.models.patient.community.forum.ForumResponse

fun makeForumEntity(
    forumResponse: ForumResponse,
    userId: String,
    isLiked: Boolean,
    isFavorite: Boolean
): Forum {
    return Forum(
        id = forumResponse.id,
        userId = userId,
        theme = forumResponse.theme,
        title = forumResponse.title,
        commentResponse = forumResponse.comments,
        likes = forumResponse.likesAmount,
        comments = forumResponse.commentsAmount,
        timestamp = forumResponse.createdAt,
        isLiked = mutableStateOf(isLiked),
        isFavorite = mutableStateOf(isFavorite)
    )
}
