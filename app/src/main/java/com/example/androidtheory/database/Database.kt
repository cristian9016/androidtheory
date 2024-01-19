package com.example.androidtheory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidtheory.model.Question

@Database(entities = [Question::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}

object Database {
    lateinit var database: AppDatabase
    fun createDatabase(context: Context) {
        database = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "questions-database",
        ).build()
    }
}
