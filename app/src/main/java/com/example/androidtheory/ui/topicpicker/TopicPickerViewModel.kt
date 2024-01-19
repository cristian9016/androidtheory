package com.example.androidtheory.ui.topicpicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtheory.repository.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopicPickerViewModel : ViewModel() {

    private val repository = QuestionRepository()

    private val _uiState = MutableStateFlow(true)
    val uiState: StateFlow<Boolean> = _uiState.asStateFlow()

    fun loadQuestionsIntoDB(questions: String?) {
        viewModelScope.launch {
            repository.addQuestionsToDB(questions)
            _uiState.value = false
        }
    }
}
