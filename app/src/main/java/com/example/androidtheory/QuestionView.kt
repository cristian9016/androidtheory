package com.example.androidtheory

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun QuestionView(
    modifier: Modifier = Modifier,
    topic: String,
) {
    Text(text = "this is Question view: $topic")
}
