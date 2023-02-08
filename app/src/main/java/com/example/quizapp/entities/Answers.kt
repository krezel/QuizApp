package com.example.quizapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Answers (
    @PrimaryKey(false)
    val answer:String,
    val correctAnswer:Boolean,
    val question:String
        )
