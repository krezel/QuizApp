package com.example.quizapp

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.example.quizapp.databinding.ActivityQuestionBinding
import kotlinx.coroutines.*


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
            Quiz().questionAndAnswersText(tvAnswerList)
            Quiz().buttonAnswer(buttonList)
        }
    }
    inner class Quiz{
        val dao = QuestionDatabase.getInstance(this@QuestionActivity).questionDao
        suspend fun questionAndAnswersText(textList: List<TextView>){
            val listQuestion = dao.getQuestion()
            binding.tvQ.text = listQuestion.first()
            val listQuestionWithAnswers = dao.getQuestionWithAnswers(listQuestion.first())
            for (i in textList.indices) {
                textList[i].text = listQuestionWithAnswers[0].answers[i].answer
            }
        }
        @OptIn(DelicateCoroutinesApi::class)
        suspend fun buttonAnswer(buttonList: List<AppCompatImageButton>) {
            val listQuestion = dao.getQuestion()
            val listQuestionWithAnswers = dao.getQuestionWithAnswers(listQuestion.first())
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
                        runBlocking {
                            for (m in buttonList.indices){
                                val correct = listQuestionWithAnswers[0].answers[m].correctAnswer
                                if (correct)
                                buttonList[m].background.colorFilter = BlendModeColorFilter(Color.parseColor("#03fc0b"), BlendMode.COLOR)
                            }
                        }
                    }
                }
            }
        }
    }
    }