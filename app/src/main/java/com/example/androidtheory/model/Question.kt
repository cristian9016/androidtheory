package com.example.androidtheory.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(
    @PrimaryKey
    val id: Int,
    val topic: String,
    val title: String,
    val answer: String,
)
