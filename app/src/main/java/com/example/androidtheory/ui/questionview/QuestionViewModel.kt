package com.example.androidtheory.ui.questionview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidtheory.model.Question
import com.example.androidtheory.repository.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class QuestionViewModel(private val topic: String) : ViewModel() {

    private val repository = QuestionRepository()

    private val _uiState: MutableStateFlow<Question?> = MutableStateFlow(null)
    val uiState: StateFlow<Question?> = _uiState.asStateFlow()

    init {
        getQuestion()
    }

    fun getQuestion() {
        viewModelScope.launch {
            repository.getCountByTopic(topic)
                .take(1)
                .collect {
                    val questionId: Int = it.random()
                    searchQuestion(questionId)
                }
        }
    }

    private fun searchQuestion(index: Int) {
        viewModelScope.launch {
            repository.getQuestionByIndexAndTopic(index)
                .collect {
                    _uiState.value = it
                }
        }
    }

    companion object {
        fun Factory(topic: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                QuestionViewModel(topic)
            }
        }
    }
}
