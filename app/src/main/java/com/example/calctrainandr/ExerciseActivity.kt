package com.example.calctrainandr

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class ExerciseActivity : AppCompatActivity() {

    private var correctAnswers = 0
    private var totalQuestions = 20
    private var currentQuestion = 0
    private var fixedNumber: Int? = null
    private var currentFirstNumber = 0
    private var currentSecondNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val questionText = findViewById<TextView>(R.id.questionText)
        val inputAnswer = findViewById<EditText>(R.id.inputAnswer)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val resultText = findViewById<TextView>(R.id.resultText)
        val btnBack = findViewById<Button>(R.id.btnBack) // Кнопка возврата

        val mode = intent.getStringExtra("mode")
        fixedNumber = intent.getIntExtra("fixedNumber", 0)

        generateNewQuestion()

        btnSubmit.setOnClickListener {
            val userAnswer = inputAnswer.text.toString().toIntOrNull()
            if (userAnswer == currentFirstNumber * currentSecondNumber) {
                correctAnswers++
                resultText.text = "Правильный ответ"
            } else {
                resultText.text = "Неверный ответ"
            }
            currentQuestion++
            if (currentQuestion < totalQuestions) {
                generateNewQuestion()
            } else {
                val score = (correctAnswers.toDouble() / totalQuestions) * 100
                questionText.text = "Тест завершен! Правильных ответов: $correctAnswers/$totalQuestions ($score%)"
                btnSubmit.isEnabled = false
            }
        }

        // Обработка нажатия на кнопку "Назад"
        btnBack.setOnClickListener {
            finish() // Закрытие текущей активности и возврат на предыдущую
        }
    }

    private fun generateNewQuestion() {
        val questionText = findViewById<TextView>(R.id.questionText)
        if (fixedNumber != 0) {
            currentFirstNumber = fixedNumber!!
        } else {
            currentFirstNumber = Random.nextInt(2, 10)
        }
        currentSecondNumber = Random.nextInt(2, 10)
        questionText.text = "Сколько будет $currentFirstNumber * $currentSecondNumber?"
    }
}
