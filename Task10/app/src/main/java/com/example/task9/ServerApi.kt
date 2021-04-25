package com.example.task9

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ServerApi {
    companion object Urls {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val REQUEST = "weather" + // текущая погода
                "?id=693805" +  // Симферополь
                "&units=metric" + // градусы Цельсия
                "&appid=30796e96fd16433e49bdf7b5fd4ac746"
    }

    @GET(Urls.REQUEST)
    suspend fun getMessage(): Response<JSONData>
}