package com.example.cardiosurgeryillustrator.models.mock.patient

import com.example.cardiosurgeryillustrator.models.patient.community.Comment
import com.example.cardiosurgeryillustrator.models.patient.community.Topic
import com.example.cardiosurgeryillustrator.models.patient.community.User

object CommunityMock {
    fun getMockUsers(): List<User> {
        return listOf(
            User(id = "1", avatarUrl = "avatar_1"),
            User(id = "2", avatarUrl = "avatar_2")
        )
    }

    fun getMockTopics(): List<Topic> {
        val users = getMockUsers()
        return listOf(
            Topic(
                id = "1",
                user = users[0],
                theme = "Pós Operatório",
                title = "Como foi seu pós operatório?",
                likes = 2000,
                comments = 500,
                timestamp = System.currentTimeMillis()
            ),
            Topic(
                id = "2",
                user = users[1],
                theme = "Dúvidas Gerais",
                title = "Quais são as principais dúvidas?",
                likes = 1500,
                comments = 300,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    fun getMockComments(): List<Comment> {
        val topics = getMockTopics()
        return listOf(
            Comment(
                id = "1",
                topicId = topics[0].id,
                commentId = "",
                userId = "1",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                likes = 10,
                isCommentable = true,
                timestamp = System.currentTimeMillis()
            ),
            Comment(
                id = "2",
                topicId = topics[1].id,
                commentId = "",
                userId = "2",
                content = "Outro comentário interessante...",
                likes = 5,
                isCommentable = true,
                timestamp = System.currentTimeMillis()
            )
        )
    }
}