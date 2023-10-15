package com.example.quizapp.ui.screens

data class QuizUiState(
    val question: String,
    val choices: List<String>,
    val correctChoice: String,
    var score: Int,
    val type: String,
    val difficulty: String,
    val totalQuestions: Int,
    var answerState: Boolean
)