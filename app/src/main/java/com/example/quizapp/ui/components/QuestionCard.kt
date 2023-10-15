package com.example.quizapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.ui.theme.QuizAppTheme

@Composable
fun QuestionCard(
    score: Int,
    type: String,
    difficulty: String,
    question: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.background)
            .clip(MaterialTheme.shapes.large)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "Type: $type",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "Difficulty: $difficulty",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                Text(text = "Score: $score", style = MaterialTheme.typography.titleMedium)
            }


            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = question,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
fun QuestionCardPreview() {
    QuizAppTheme {
        QuestionCard(
            score = 0,
            type = "Art",
            difficulty = "Easy",
            question = "Why so serious?"
        )
    }
}