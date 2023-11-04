package com.example.quizapp.ui.screens.quiz

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.network.QuizApi
import com.example.quizapp.network.QuizResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class QuizViewModel : ViewModel() {
    private val _uiState = mutableStateOf(
        QuizUiState(
            question = "",
            choices = listOf(),
            correctChoice = "",
            score = 0,
            type = "",
            difficulty = "",
            totalQuestions = 0,
            answerState = false,
            isCorrect = false
        )
    )
    val uiState: MutableState<QuizUiState> = _uiState

    init {
        getQuestion()
    }

    fun onAnswerSelected(answer: String) {
        if (answer == _uiState.value.correctChoice && !_uiState.value.answerState) {
            when (uiState.value.difficulty) {
                "easy" -> _uiState.value.score += 1
                "medium" -> _uiState.value.score += 2
                "hard" -> _uiState.value.score += 3
            }
            _uiState.value = _uiState.value.copy(
                totalQuestions = _uiState.value.totalQuestions + 1,
                answerState = true,
                isCorrect = true
            )
        } else if (answer != _uiState.value.correctChoice && !_uiState.value.answerState) {
            _uiState.value = _uiState.value.copy(
                answerState = true,
                isCorrect = false,
                tries = _uiState.value.tries - 1
            )
        }
    }


    fun getQuestion() {
        _uiState.value = _uiState.value.copy(
            answerState = false,
        )
        Log.d("QuizViewModel", "getQuestion: ")
        viewModelScope.launch(Dispatchers.IO) {
            val response = QuizApi.retrofitService.getQuestion(1, 18)
            val parsedResponse = parseHtml(response)
            _uiState.value = _uiState.value.copy(
                question = parsedResponse.results[0].question,
                choices = (parsedResponse.results[0].incorrectAnswers + parsedResponse.results[0].correctAnswer).shuffled(),
                correctChoice = parsedResponse.results[0].correctAnswer,
                type = parsedResponse.results[0].type,
                difficulty = parsedResponse.results[0].difficulty,
            )
        }
    }

    private fun parseHtml(response: QuizResponse): QuizResponse {
        val question = Jsoup.parse(response.results[0].question).text()
        val correctAnswer = Jsoup.parse(response.results[0].correctAnswer).text()
        val incorrectAnswers = response.results[0].incorrectAnswers.map {
            Jsoup.parse(it).text()
        }
        return response.copy(
            results = listOf(
                response.results[0].copy(
                    question = question,
                    correctAnswer = correctAnswer,
                    incorrectAnswers = incorrectAnswers
                )
            )
        )
    }

    fun resetQuiz() {
        _uiState.value = QuizUiState(
            question = "",
            choices = listOf(),
            correctChoice = "",
            score = 0,
            type = "",
            difficulty = "",
            totalQuestions = 0,
            answerState = false,
            isCorrect = false
        )
    }
}
