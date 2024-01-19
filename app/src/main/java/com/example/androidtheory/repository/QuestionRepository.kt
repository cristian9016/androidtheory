package com.example.androidtheory.repository

import com.example.androidtheory.database.Database
import com.example.androidtheory.model.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow

class QuestionRepository {

    private val dao = Database.database.questionDao()
    private val gson = Gson()

    fun getCountByTopic(topic: String): Flow<List<Int>> {
        return dao.getIdsForTopic(topic)
    }

    fun getQuestionByIndexAndTopic(index: Int): Flow<Question> {
        return dao.getQuestion(index)
    }

    suspend fun addQuestionsToDB(questions: String?) {
        val listQuestionType = object : TypeToken<List<Question>>() {}.type
        val questionList: List<Question> = gson.fromJson(questions, listQuestionType)
        dao.insertAll(questionList)
    }
}
