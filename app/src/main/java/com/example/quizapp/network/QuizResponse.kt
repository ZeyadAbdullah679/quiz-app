package com.example.quizapp.network


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizResponse(
    @SerialName("response_code")
    val responseCode: Int,
    @SerialName("results")
    val results: List<Result>
)