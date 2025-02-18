package com.example.cardiosurgeryillustrator.core.services

import com.example.cardiosurgeryillustrator.models.patient.nearby_clinics.OverpassResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import android.util.Log
import com.example.cardiosurgeryillustrator.models.patient.nearby_clinics.Element
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response

interface OverpassService {
    @GET("interpreter")
    fun searchClinics(@Query("data") query: String): Call<OverpassResponse>
}



fun fetchNearbyClinics(latitude: Double, longitude: Double, onResult: (List<Element>) -> Unit) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://overpass-api.de/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(OverpassService::class.java)

    val query = """
        [out:json];
        (
          node["amenity"="clinic"](around:5000,$latitude,$longitude);
          node["healthcare"="clinic"](around:5000,$latitude,$longitude);
        );
        out body;
    """.trimIndent()

    service.searchClinics(query).enqueue(object : Callback<OverpassResponse> {
        override fun onResponse(call: Call<OverpassResponse>, response: Response<OverpassResponse>) {
            if (response.isSuccessful) {
                val clinics = response.body()?.elements ?: emptyList()
                onResult(clinics)
            } else {
                Log.e("Overpass", "Erro na resposta: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<OverpassResponse>, t: Throwable) {
            Log.e("Overpass", "Falha na requisição", t)
        }
    })
}