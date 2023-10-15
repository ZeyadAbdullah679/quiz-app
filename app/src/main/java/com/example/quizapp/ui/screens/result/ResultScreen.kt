package com.example.quizapp.ui.screens.result

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizapp.R
import com.example.quizapp.ui.theme.QuizAppTheme

@Composable
fun ResultScreen(
    name: String,
    score: Int,
    totalQuestions: Int,
    onRestartQuiz: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_lnrjukkt))
    val progress by animateLottieCompositionAsState(composition)
    val compositionCup by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_lnrnh0xo))
    BackHandler {
        onRestartQuiz()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = compositionCup,
            modifier = Modifier.size(250.dp)
        )
        Text(
            text = "Quiz Results",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Name: $name",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Score: $score",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Total Questions: $totalQuestions",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.weight(1f))


        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onRestartQuiz() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Restart Quiz",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
        }
    }
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    QuizAppTheme {
        ResultScreen("Zeyad", 1, 3, onRestartQuiz = {})
    }
}
