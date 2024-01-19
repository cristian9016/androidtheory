package com.example.androidtheory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopicPickerView(
    modifier: Modifier = Modifier,
    onClickTopic: (String) -> Unit,
) {
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
