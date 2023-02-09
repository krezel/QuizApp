package com.example.quizapp

import androidx.room.*
import com.example.quizapp.entities.Answers
import com.example.quizapp.entities.Questions
import com.example.quizapp.relations.QuestionWithAnswers

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Questions)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answer: Answers)

    @Query("DELETE FROM Questions")
    suspend fun deleteQ()
    @Query("DELETE FROM Answers")
    suspend fun deleteA()

    @Query("DELETE FROM Answers WHERE question = :question")
    suspend fun deleteAnswers(question:String)

    @Query("DELETE FROM Questions WHERE question = :question")
    suspend fun deleteQuestion(question:String)

    @Transaction
    @Query("SELECT * FROM Questions WHERE question = :question")
    suspend fun getQuestionWithAnswers(question: String) : List<QuestionWithAnswers>

    @Query("SELECT * FROM QUESTIONS")
    suspend fun getQuestion() : List<String>


}