package com.example.androidtheory.ui.topicpicker

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.androidtheory.R
import com.example.androidtheory.model.topics
import com.example.androidtheory.preferences.Prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

@Composable
fun TopicPickerView(
    modifier: Modifier = Modifier,
    topicPickerViewModel: TopicPickerViewModel = viewModel(),
    onClickTopic: (String) -> Unit,
) {
    val context = LocalContext.current
    CoroutineScope(Dispatchers.Default).launch {
        if (Prefs.getPrefs()?.isDataLoaded()?.not() != false) {
            val androidQuestions = getJsonDataFromAsset(context)
            topicPickerViewModel.loadQuestionsIntoDB(androidQuestions)
        } else {
            topicPickerViewModel.questionsAreLoaded()
        }
    }

    val isLoading = topicPickerViewModel.uiLoading.collectAsState()
    if (isLoading.value) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.size(80.dp))
        }
    } else {
        LazyColumn(modifier = modifier.padding(16.dp)) {
            items(topics) { item ->
                AsyncImage(
                    modifier = Modifier.fillMaxWidth()
                        .clickable { onClickTopic.invoke(item.name) },
                    model = item.image,
                    contentDescription = null,
                )
            }
        }
    }
}

fun getJsonDataFromAsset(context: Context): String? {
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
