package com.example.cardiosurgeryillustrator.ui.screens.patient.chatbot

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

object ChatBotApi {
    private const val BASE_URL =
        "https://chatbot-rasa-fkdbekeafjbxecgb.canadacentral-01.azurewebsites.net/webhooks/rest/webhook"

    private val client = OkHttpClient()

    suspend fun sendMessage(message: String): String? {
        return withContext(Dispatchers.IO) {
            try {
                val json = JSONObject().apply {
                    put("sender", "test_user")
                    put("message", message)
                }
                val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

                val request = Request.Builder()
                    .url(BASE_URL)
                    .post(body)
                    .build()

                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    val jsonArray = JSONArray(responseBody)
                    if (jsonArray.length() > 0) {
                        jsonArray.getJSONObject(0).getString("text")
                    } else {
                        null
                    }
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
