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
import com.example.quizapp.ui.theme.QuizAppTheme

enum class Screen {
    HOME,
    QUIZ,
    RESULT,
}

@Composable
fun QuizApp(
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = remember { HomeViewModel() }
) {
    NavHost(navController = navController, startDestination = Screen.HOME.name) {
        composable(Screen.HOME.name) {
            HomeScreen(
                name = homeViewModel.name.value,
                onNameChange = { homeViewModel.onNameChange(it) },
                onClickStartQuiz = { navController.navigate(Screen.QUIZ.name) },
                highestScore = 120
            )
        }
        composable(Screen.QUIZ.name) {

        }
        composable(Screen.RESULT.name) {

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