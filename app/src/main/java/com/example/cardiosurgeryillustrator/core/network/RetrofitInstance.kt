package com.example.cardiosurgeryillustrator.core.network

import com.example.cardiosurgeryillustrator.core.services.AssistantService
import com.example.cardiosurgeryillustrator.core.services.ModuleService
import com.example.cardiosurgeryillustrator.core.services.SubjectService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:8080"
    private const val CHATBOT_URL =
        "https://chatbot-rasa-fkdbekeafjbxecgb.canadacentral-01.azurewebsites.net"

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
}