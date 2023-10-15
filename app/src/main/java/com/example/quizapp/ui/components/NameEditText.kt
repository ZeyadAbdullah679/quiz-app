package com.example.quizapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.R
import com.example.quizapp.ui.theme.QuizAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameEditText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        textStyle = MaterialTheme.typography.titleMedium,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth(),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.enter_your_name),
                style = MaterialTheme.typography.titleMedium,
            )
        },
    )
}


@Preview
@Composable
fun SearchTextFieldPreview() {
    QuizAppTheme {
        NameEditText(
            value = "",
            onValueChange = {}
        )
    }
}

