package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.entities.Answers
import com.example.quizapp.entities.Questions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnStart.setOnClickListener {
            Intent(this,QuestionActivity::class.java).also {
                startActivity(it)
            }
        }
    }
    suspend fun loadDatabase(){
        val dao = QuestionDatabase.getInstance(this).questionDao
        val pytanie = Questions("Ile mam lat?")
        val odpowiedz1 = Answers("23",true,"Ile mam lat?")
        val odpowiedz2 = Answers("21",false,"Ile mam lat?")
        val odpowiedz3 = Answers("22",false,"Ile mam lat?")
        val odpowiedz4 = Answers("24",false,"Ile mam lat?")
        dao.insertQuestion(pytanie)
        dao.insertAnswer(odpowiedz1)
        dao.insertAnswer(odpowiedz2)
        dao.insertAnswer(odpowiedz3)
        dao.insertAnswer(odpowiedz4)
    }
}