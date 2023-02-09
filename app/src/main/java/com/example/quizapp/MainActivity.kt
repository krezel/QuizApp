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
            GlobalScope.launch(Dispatchers.IO){
                //val dao = QuestionDatabase.getInstance(this@MainActivity).questionDao
                //dao.deleteQ()
                //dao.deleteA()
                loadDatabase()
            }
        }
    }
    suspend fun loadDatabase(){
        val dao = QuestionDatabase.getInstance(this).questionDao
        val pytanie2 = Questions("Ile to 5+12")
        val listAnswer2 = listOf(
            Answers("15",false,"Ile to 5+12"),
            Answers("17",true,"Ile to 5+12"),
            Answers("14",false,"Ile to 5+12"),
            Answers("18",false,"Ile to 5+12")
        )
        dao.insertQuestion(pytanie2)
        for (answer in listAnswer2)
            dao.insertAnswer(answer)

        val pytanie3 = Questions("Jakie miasto jest stolicą Polski?")
        val listAnswer3 = listOf(
            Answers("Warszawa",true,"Jakie miasto jest stolicą Polski?"),
            Answers("Łódź",false,"Jakie miasto jest stolicą Polski?"),
            Answers("Poznań",false,"Jakie miasto jest stolicą Polski?"),
            Answers("Wrocław",false,"Jakie miasto jest stolicą Polski?")
        )
        dao.insertQuestion(pytanie3)
        for (answer in listAnswer3)
            dao.insertAnswer(answer)

        val pytanie4 = Questions("Który makroskładnik jest budulcem mięśni?")
        val listAnswer4 = listOf(
            Answers("Węglowodany",false,"Który makroskładnik jest budulcem mięśni?"),
            Answers("Tłuszcze",false,"Który makroskładnik jest budulcem mięśni?"),
            Answers("Białka",true,"Który makroskładnik jest budulcem mięśni?"),
            Answers("Cukry",false,"Który makroskładnik jest budulcem mięśni?")
        )
        dao.insertQuestion(pytanie4)
        for (answer in listAnswer4)
            dao.insertAnswer(answer)

        val pytanie5 = Questions("Ile nóg ma kot?")
        val listAnswer5 = listOf(
            Answers("6",false,"Ile nóg ma kot?"),
            Answers("4",true,"Ile nóg ma kot?"),
            Answers("2",false,"Ile nóg ma kot?"),
            Answers("3",false,"Ile nóg ma kot?")
        )
        dao.insertQuestion(pytanie5)
        for (answer in listAnswer5)
            dao.insertAnswer(answer)

    }
}