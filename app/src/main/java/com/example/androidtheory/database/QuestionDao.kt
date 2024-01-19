package com.example.androidtheory.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidtheory.model.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Query("select * from Question where `id` like :index")
    fun getQuestion(index: Int): Flow<Question>

    @Query("select id from Question where topic like :topic")
    fun getIdsForTopic(topic: String): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<Question>)
}
