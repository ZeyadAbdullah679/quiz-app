package com.example.quizapp.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.MainActivity
import com.example.quizapp.ui.screens.home.HomeScreen
import com.example.quizapp.ui.screens.home.HomeViewModel
import com.example.quizapp.ui.screens.quiz.QuizScreen
import com.example.quizapp.ui.screens.quiz.QuizViewModel
import com.example.quizapp.ui.screens.result.ResultScreen
import com.example.quizapp.ui.theme.QuizAppTheme
import com.example.quizapp.util.SharedPrefsUtil

enum class Screen {
    HOME,
    QUIZ,
    RESULT,
}

@Composable
fun QuizApp(
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = remember { HomeViewModel() },
    quizViewModel: QuizViewModel = remember { QuizViewModel() },
) {
    val context = LocalContext.current
    val activity = context as MainActivity
    var highestScoreName by rememberSaveable {
        mutableStateOf(SharedPrefsUtil(context).getUserName())
    }
    var highestScore by rememberSaveable {
        mutableIntStateOf(SharedPrefsUtil(context).getUserScore())
    }
    NavHost(navController = navController, startDestination = Screen.HOME.name) {
        composable(Screen.HOME.name) {
            HomeScreen(
                name = homeViewModel.name.value,
                onNameChange = { homeViewModel.onNameChange(it) },
                onClickStartQuiz = {
                    if (homeViewModel.name.value.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Your name cannot be empty!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (homeViewModel.name.value.length > 12) {
                        Toast.makeText(
                            context,
                            "Your name cannot be more than 12 characters!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (homeViewModel.name.value.length < 2) {
                        Toast.makeText(
                            context,
                            "Your name cannot be less than 2 characters!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        navController.navigate(Screen.QUIZ.name)
                    }
                },
                highestScore = highestScore,
                highestScoreName = highestScoreName ?: "",
                onPressBack = { activity.finish() }
            )
        }
        composable(Screen.QUIZ.name) {
            QuizScreen(
                uiState = quizViewModel.uiState.value,
                onAnswerSelected = { quizViewModel.onAnswerSelected(it) },
                onClickNext = { quizViewModel.getQuestion() },
                onClickResults = {
                    navController.navigate(Screen.RESULT.name)
                },
                onRestartQuiz = {
                    navController.navigate(Screen.HOME.name)
                    quizViewModel.getQuestion()
                    homeViewModel.resetName()
                    quizViewModel.resetQuiz()
                }
            )
        }
        composable(Screen.RESULT.name) {
            ResultScreen(
                name = homeViewModel.name.value,
                score = quizViewModel.uiState.value.score,
                totalQuestions = quizViewModel.uiState.value.totalQuestions,
                onRestartQuiz = {
                    if (quizViewModel.uiState.value.score > highestScore) {
                        SharedPrefsUtil(context).saveUserScore(quizViewModel.uiState.value.score)
                        SharedPrefsUtil(context).saveUserName(homeViewModel.name.value)
                        highestScore = quizViewModel.uiState.value.score
                        highestScoreName = homeViewModel.name.value
                    }
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