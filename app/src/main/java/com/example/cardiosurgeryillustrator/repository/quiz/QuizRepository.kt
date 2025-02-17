package com.example.cardiosurgeryillustrator.repository.quiz


import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.student.quiz.question.Question
import com.example.cardiosurgeryillustrator.models.student.quiz.question.QuestionResponse
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.CreateQuizRequest
import com.example.cardiosurgeryillustrator.models.student.quiz.quiz.Quiz
import retrofit2.Response


class QuizRepository() {

    suspend fun createQuiz(quiz: CreateQuizRequest): Response<Quiz> {
        return RetrofitInstance.quizService.createQuiz(quiz)
    }

    suspend fun getQuizzes(): Response<List<Quiz>> {
        val response = RetrofitInstance.quizService.getQuizzes()
        println("Resposta da API getQuizzes: ${response.body()}")
        return response
    }

    suspend fun getQuizById(quizId: String): Quiz {
        return RetrofitInstance.quizService.getQuizById(quizId)
    }

    suspend fun addQuestionToQuiz(questionId: String, quizId: String): Response<QuestionResponse> {
        return RetrofitInstance.quizService.addQuestionToQuiz(questionId, quizId)
    }

}