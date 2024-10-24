package com.example.calctrainandr

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAllNumbers = findViewById<Button>(R.id.btnAllNumbers)
        val btnSelective = findViewById<Button>(R.id.btnSelective)
        val inputNumber = findViewById<EditText>(R.id.inputNumber)

        btnAllNumbers.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("mode", "all")
            startActivity(intent)
        }

        btnSelective.setOnClickListener {
            val number = inputNumber.text.toString().toIntOrNull()
            if (number in 2..9) {
                val intent = Intent(this, ExerciseActivity::class.java)
                intent.putExtra("mode", "selective")
                intent.putExtra("fixedNumber", number)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Введите число от 2 до 9", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
