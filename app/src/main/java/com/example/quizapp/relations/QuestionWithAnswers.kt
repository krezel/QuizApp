package com.example.quizapp.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.quizapp.entities.Answers
import com.example.quizapp.entities.Questions

data class QuestionWithAnswers(
    @Embedded val question:Questions,
    @Relation(
        parentColumn = "question",
        entityColumn = "question"
    )
    val answers: List<Answers>
)
