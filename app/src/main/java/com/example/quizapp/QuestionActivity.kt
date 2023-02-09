package com.example.quizapp

import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.example.quizapp.databinding.ActivityQuestionBinding
import kotlinx.coroutines.*
import kotlin.random.Random


class QuestionActivity : AppCompatActivity() {
    var points = 0
    private lateinit var binding: ActivityQuestionBinding
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val buttonList =
            listOf(binding.btnAnswer1, binding.btnAnswer2, binding.btnAnswer3, binding.btnAnswer4)
        val tvAnswerList = listOf(binding.tvA1, binding.tvA2, binding.tvA3, binding.tvA4)
        GlobalScope.launch(Dispatchers.Main) {
            val random = Random
            val i = 0
            Quiz().questionAndAnswersText(tvAnswerList,i)
            Quiz().buttonAnswer(buttonList,i)
        }
    }
    inner class Quiz{
        private val dao = QuestionDatabase.getInstance(this@QuestionActivity).questionDao
        suspend fun questionAndAnswersText(textList: List<TextView>,rand:Int){
            val listQuestion = dao.getQuestion()
            //ten indeks wystarczy, zeby zmienic pytanie i odpowiedzi
            binding.tvQ.text = listQuestion[rand]
            val listQuestionWithAnswers = dao.getQuestionWithAnswers(listQuestion[rand])
            for (i in textList.indices) {
                textList[i].text = listQuestionWithAnswers[0].answers[i].answer
            }
        }
        suspend fun buttonAnswer(buttonList: List<AppCompatImageButton>,rand:Int) {
            val listQuestion = dao.getQuestion()
            val listQuestionWithAnswers = dao.getQuestionWithAnswers(listQuestion[rand])
            for (i in buttonList.indices) {
                val isCorrect = listQuestionWithAnswers[0].answers[i].correctAnswer
                buttonList[i].setOnClickListener {
                    for (btn in buttonList)
                        btn.isClickable = false
                    if (isCorrect) {
                        points++
                        buttonList[i].background.colorFilter =
                            BlendModeColorFilter(Color.parseColor("#03fc0b"), BlendMode.COLOR)
                    } else{
                        buttonList[i].background.colorFilter =
                            BlendModeColorFilter(Color.parseColor("#fc0328"), BlendMode.COLOR)
                            for (m in buttonList.indices){
                                val correct = listQuestionWithAnswers[0].answers[m].correctAnswer
                                if (correct)
                                buttonList[m].background.colorFilter = BlendModeColorFilter(Color.parseColor("#03fc0b"), BlendMode.COLOR)
                        }
                    }
                    GlobalScope.launch(Dispatchers.IO){
                        //dao.deleteQ()
                        //dao.deleteA()
                        dao.deleteAnswers(listQuestion[rand])
                        dao.deleteQuestion(listQuestion[rand])
                    }
                    Intent(this@QuestionActivity,QuestionActivity::class.java).also {
                        startActivity(it)
                    }
                }
            }
        }
    }
    }