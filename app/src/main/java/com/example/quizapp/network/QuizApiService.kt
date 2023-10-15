package com.example.quizapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Query

private const val BASE_URL = "https://opentdb.com/"

private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface QuizApiService {
    @POST("api.php")
    suspend fun getQuestion(@Query("amount") amount: Int): QuizResponse
}

object QuizApi {
    val retrofitService: QuizApiService by lazy {
        retrofit.create(QuizApiService::class.java)
    }
}
