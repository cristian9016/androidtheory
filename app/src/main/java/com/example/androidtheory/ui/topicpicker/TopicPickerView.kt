package com.example.androidtheory.ui.topicpicker

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidtheory.R
import java.io.IOException

@Composable
fun TopicPickerView(
    modifier: Modifier = Modifier,
    topicPickerViewModel: TopicPickerViewModel = viewModel(),
    onClickTopic: (String) -> Unit,
) {
    val androidQuestions = getJsonDataFromAsset(LocalContext.current, "android")
    val isLoading = topicPickerViewModel.uiState.collectAsState()
    topicPickerViewModel.loadQuestionsIntoDB(androidQuestions)
    if (isLoading.value) {
        CircularProgressIndicator()
    } else {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(modifier = Modifier.width(150.dp), onClick = { onClickTopic("android") }) {
                Text(text = "Android")
            }
            Button(modifier = Modifier.width(150.dp), onClick = { onClickTopic("kotlin") }) {
                Text(text = "Kotlin")
            }
        }
    }
}

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        val file = context.resources.openRawResource(R.raw.android)
        jsonString = file.bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}
