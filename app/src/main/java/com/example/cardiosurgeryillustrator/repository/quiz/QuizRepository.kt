package com.example.cardiosurgeryillustrator.repository.quiz


import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.CreateQuizRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.Quiz
import retrofit2.Response
import android.content.Context
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
class QuizRepository(private val context: Context) {

    suspend fun createQuiz(quiz: CreateQuizRequest): Response<Quiz> {
        val token = getAdminToken()
        return RetrofitInstance.quizService.createQuiz(quiz, token)
    }

    suspend fun getQuizzes(): Response<List<Quiz>> {
        val token = getAdminToken()
        val response = RetrofitInstance.quizService.getQuizzes(token)
        println("Resposta da API getQuizzes: ${response.body()}")
        return response
    }

    suspend fun getQuizById(quizId: String): Quiz {
        val token = getAdminToken()
        return RetrofitInstance.quizService.getQuizById(quizId, token)
    }

    private fun getAdminToken(): String {
        return runBlocking {
            "Bearer " + (DataStoreUtils.readTokenAdmin(context).first() ?: "")
        }
    }
}