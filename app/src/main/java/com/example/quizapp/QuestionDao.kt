package com.example.quizapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.quizapp.entities.Answers
import com.example.quizapp.entities.Questions
import com.example.quizapp.relations.QuestionWithAnswers

@Dao
interface QuestionDao {
    @Insert
    suspend fun insertQuestion(question: Questions)

    @Insert
    suspend fun insertAnswer(answer: Answers)

    @Transaction
    @Query("SELECT * FROM Questions WHERE question = :question")
    suspend fun getQuestionWithAnswers(question: String) : List<QuestionWithAnswers>

    @Query("SELECT * FROM QUESTIONS")
    suspend fun getQuestion() : List<String>

}