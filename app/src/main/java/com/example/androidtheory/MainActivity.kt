package com.example.androidtheory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidtheory.ui.questionview.QuestionView
import com.example.androidtheory.ui.theme.AndroidTheoryTheme
import com.example.androidtheory.ui.topicpicker.TopicPickerView

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTheoryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "topic",
                        enterTransition = { EnterTransition.None },
                        exitTransition = { ExitTransition.None },
                    ) {
                        composable("topic") {
                            TopicPickerView(modifier = Modifier.fillMaxSize()) { topic ->
                                navController.navigate("questions/$topic")
                            }
                        }
                        composable("questions/{topic}") { backStackEntry ->
                            QuestionView(
                                modifier = Modifier.fillMaxSize(),
                                topic = backStackEntry.arguments?.getString("topic") ?: "",
                            ) {
                                navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }
}
