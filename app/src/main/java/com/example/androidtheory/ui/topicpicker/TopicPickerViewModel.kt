package com.example.androidtheory.ui.topicpicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtheory.preferences.Prefs
import com.example.androidtheory.repository.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopicPickerViewModel : ViewModel() {

    private val prefs = Prefs.getPrefs()
    private val repository = QuestionRepository()

    private val _uiLoading = MutableStateFlow(true)
    val uiLoading: StateFlow<Boolean> = _uiLoading.asStateFlow()

    fun loadQuestionsIntoDB(questions: String?) {
        _uiLoading.value = true
        viewModelScope.launch {
            repository.addQuestionsToDB(questions)
            prefs?.saveDataLoaded(true)
            _uiLoading.value = false
        }
    }

    fun questionsAreLoaded() {
        _uiLoading.value = false
    }
}
