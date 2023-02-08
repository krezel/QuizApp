package com.example.quizapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizapp.entities.Answers
import com.example.quizapp.entities.Questions

@Database(entities = [Questions::class,Answers::class], version = 1)
abstract class QuestionDatabase : RoomDatabase(){
    abstract val questionDao : QuestionDao
    companion object{
        @Volatile
        private var INSTANCE:QuestionDatabase?=null

        fun getInstance(context:Context): QuestionDatabase {
            synchronized(this){
                return INSTANCE?: Room.databaseBuilder(
                    context.applicationContext,
                    QuestionDatabase::class.java,
                    "quiz_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}