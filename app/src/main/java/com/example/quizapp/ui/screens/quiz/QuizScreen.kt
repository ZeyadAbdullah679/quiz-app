package com.example.quizapp.ui.screens.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizapp.R
import com.example.quizapp.ui.components.QuestionCard
import com.example.quizapp.ui.theme.QuizAppTheme

@Composable
fun QuizScreen(
    uiState: QuizUiState,
    onAnswerSelected: (String) -> Unit,
    onClickNext: () -> Unit,
    onClickResults: () -> Unit,
    onRestartQuiz: () -> Unit
) {
    val compositionIncorrect by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_lnrketr5))
    val compositionCorrect by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_lnrkff29))
    val progressIncorrect by animateLottieCompositionAsState(compositionIncorrect)
    val progressCorrect by animateLottieCompositionAsState(compositionCorrect)
    BackHandler {
        onRestartQuiz()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        QuestionCard(
            score = uiState.score,
            type = uiState.type,
            difficulty = uiState.difficulty,
            question = uiState.question,
            lives = uiState.tries
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(uiState.choices.size) { index ->
                Button(
                    onClick = {
                        if (!uiState.answerState) {
                            onAnswerSelected(uiState.choices[index])
                            uiState.answerState = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (uiState.answerState) {
                            if (uiState.choices[index] == uiState.correctChoice) {
                                MaterialTheme.colorScheme.secondary
                            } else {
                                MaterialTheme.colorScheme.error
                            }
                        } else {
                            MaterialTheme.colorScheme.primary
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f)
                ) {
                    Text(
                        text = uiState.choices[index],
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        if (uiState.answerState && uiState.isCorrect) {
            LottieAnimation(composition = compositionCorrect, progress = { progressCorrect })
        } else if (uiState.answerState && !uiState.isCorrect) {
            LottieAnimation(composition = compositionIncorrect, progress = { progressIncorrect })
        }
        Spacer(modifier = Modifier.weight(1f))
        if (uiState.answerState) {
            Button(
                onClick = {
                    if (uiState.tries > 0) {
                        onClickNext()
                    } else {
                        onClickResults()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = if (uiState.tries > 0) "Next Question" else "Results",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    QuizAppTheme {
        QuizScreen(
            uiState = QuizUiState(
                score = 0,
                type = "True/False",
                difficulty = "Easy",
                question = "This is a sample question",
                choices = listOf("True", "False"),
                correctChoice = "True",
                totalQuestions = 2,
                answerState = true,
                isCorrect = true
            ),
            onAnswerSelected = {},
            onClickNext = {},
            onClickResults = {},
            onRestartQuiz = {}
        )
    }
}
