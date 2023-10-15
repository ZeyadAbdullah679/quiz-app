package com.example.quizapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.ui.theme.QuizAppTheme

@Composable
fun HighScoreBanner(username: String, score: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(Color.Blue), // Customize the background color
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🌟 Highest Score in the App! 🌟",
                    fontSize = 20.sp,
                    color = Color.White, // Customize the text color
                )
                Text(
                    text = "Congratulations to $username for achieving",
                    fontSize = 16.sp,
                    color = Color.White,
                )
                Text(
                    text = "an incredible score of $score!",
                    fontSize = 16.sp,
                    color = Color.White,
                )
                Text(
                    text = "Can you beat this high score? Play now and prove your skills!",
                    fontSize = 16.sp,
                    color = Color.White,
                )
                // Add a button here that redirects to your game or app
                // You can use a Button composable for this.
            }
        }
    }
}

@Preview
@Composable
fun HighScoreBannerPreview() {
    QuizAppTheme {
        HighScoreBanner("User123", 1000)
    }
}
