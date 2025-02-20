package com.example.cardiosurgeryillustrator.core.network

import android.content.Context
import com.example.cardiosurgeryillustrator.core.services.AssistantService
import com.example.cardiosurgeryillustrator.core.services.AuthService
import com.example.cardiosurgeryillustrator.core.services.CommentService
import com.example.cardiosurgeryillustrator.core.services.ModuleService
import com.example.cardiosurgeryillustrator.core.services.PasswordRecoveryService
import com.example.cardiosurgeryillustrator.core.services.QuestionService
import com.example.cardiosurgeryillustrator.core.services.ForumService
import com.example.cardiosurgeryillustrator.core.services.PatientService
import com.example.cardiosurgeryillustrator.core.services.QuizService
import com.example.cardiosurgeryillustrator.core.services.SubjectService
import com.example.cardiosurgeryillustrator.utils.DataStoreUtils
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://cardiosurgeryillustrator-api.onrender.com"
    private const val CHATBOT_URL =
        "https://chatboot-fgauhxcrbgcnffg0.canadacentral-01.azurewebsites.net"

    val moduleService: ModuleService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ModuleService::class.java)
    }

    val subjectService: SubjectService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SubjectService::class.java)
    }

    val assistantService: AssistantService by lazy {
        Retrofit.Builder()
            .baseUrl(CHATBOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AssistantService::class.java)
    }

    val quizService: QuizService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizService::class.java)
    }

    val forumService: ForumService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ForumService::class.java)
    }

    val patientService: PatientService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PatientService::class.java)
    }

    val questionService: QuestionService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionService::class.java)
    }

    val authService: AuthService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }

    val passwordRecoveryService: PasswordRecoveryService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PasswordRecoveryService::class.java)
    }

    val commentService: CommentService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommentService::class.java)
    }
}