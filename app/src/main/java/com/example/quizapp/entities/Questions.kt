package com.example.quizapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Questions (
    @PrimaryKey(false)
    val question:String
        )
