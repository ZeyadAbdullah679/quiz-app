package com.example.quizapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.screens.HomeScreen
import com.example.quizapp.ui.screens.HomeViewModel
import com.example.quizapp.ui.screens.QuizScreen
import com.example.quizapp.ui.screens.QuizViewModel
import com.example.quizapp.ui.screens.ResultScreen
import com.example.quizapp.ui.theme.QuizAppTheme

enum class Screen {
    HOME,
    QUIZ,
    RESULT,
}

@Composable
fun QuizApp(
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = remember { HomeViewModel() },
    quizViewModel: QuizViewModel = remember { QuizViewModel() }
) {
    quizViewModel.getQuestion()
    NavHost(navController = navController, startDestination = Screen.HOME.name) {
        composable(Screen.HOME.name) {
            HomeScreen(
                name = homeViewModel.name.value,
                onNameChange = { homeViewModel.onNameChange(it) },
                onClickStartQuiz = { navController.navigate(Screen.QUIZ.name) },
                highestScore = 120,
                highestScoreName = "John Doe"
            )
        }
        composable(Screen.QUIZ.name) {
            QuizScreen(
                uiState = quizViewModel.uiState.value,
                onAnswerSelected = { quizViewModel.onAnswerSelected(it) },
                onClickNext = { quizViewModel.getQuestion() }
            )
        }
        composable(Screen.RESULT.name) {
            ResultScreen(
                name = homeViewModel.name.value,
                score = quizViewModel.uiState.value.score,
                totalQuestions = quizViewModel.uiState.value.totalQuestions,
                onRestartQuiz = {
                    homeViewModel.resetName()
                    quizViewModel.resetQuiz()
                    quizViewModel.getQuestion()
                    navController.navigate(Screen.HOME.name)
                }
            )
        }
    }
}


@Preview
@Composable
fun QuizAppPreview() {
    QuizAppTheme {
        QuizApp()
    }
}