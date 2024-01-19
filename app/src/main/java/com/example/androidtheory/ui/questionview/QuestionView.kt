package com.example.androidtheory.ui.questionview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun QuestionView(
    modifier: Modifier = Modifier,
    topic: String,
    questionViewModel: QuestionViewModel = viewModel(factory = QuestionViewModel.Factory(topic)),
) {
    val question = questionViewModel.uiState.collectAsState()
    question.value?.let {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = it.title,
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(20f, TextUnitType.Sp),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()),
                text = it.answer,
                fontWeight = FontWeight.Normal,
                fontSize = TextUnit(16f, TextUnitType.Sp),
                textAlign = TextAlign.Justify,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(onClick = { questionViewModel.getQuestion() }) {
                    Text(text = "Siguiente")
                }
            }
        }
    }
}
