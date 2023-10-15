package com.example.quizapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.R
import com.example.quizapp.ui.components.NameEditText
import com.example.quizapp.ui.theme.QuizAppTheme

@Composable
fun HomeScreen(
    name: String,
    highestScoreName: String,
    highestScore: Int,
    onNameChange: (String) -> Unit,
    onClickStartQuiz: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.quiz),
            contentDescription = "Quiz Image",
            modifier = Modifier
                .size(300.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "Quizzard",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "\uD83C\uDF1F Highest Score in the App! \uD83C\uDF1F", // Display the highest score here
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = highestScoreName,
            style = TextStyle(
                fontSize = 40.sp,
                fontFamily = MaterialTheme.typography.headlineLarge.fontFamily,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            ),
        )
        Text(
            text = "$highestScore",
            style = TextStyle(
                fontSize = 50.sp,
                fontFamily = MaterialTheme.typography.headlineLarge.fontFamily,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            ),
        )
        Spacer(modifier = Modifier.weight(1f))

        NameEditText(value = name, onValueChange = onNameChange)
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            onClick = onClickStartQuiz,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Start Quiz",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    QuizAppTheme(useDarkTheme = true) {
        HomeScreen(
            "Zeyad",
            highestScore = 120,
            highestScoreName = "Zeyad",
            onNameChange = {},
            onClickStartQuiz = {})
    }
}