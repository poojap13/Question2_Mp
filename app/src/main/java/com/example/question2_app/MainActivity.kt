package com.example.question2_app

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var generateButton: Button
    private lateinit var answerEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var resultTextView: TextView

    private var currentQuestion: String = ""
    private var correctAnswer: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        generateButton = findViewById(R.id.generateButton)
        answerEditText = findViewById(R.id.answerEditText)
        submitButton = findViewById(R.id.submitButton)
        resultTextView = findViewById(R.id.resultTextView)

        val categories = arrayOf("Science", "Math", "History")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Do nothing here; the button click will handle generating a question
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onGetQuestionClick(view: View) {
        val selectedCategory = spinner.selectedItem.toString()
        when (selectedCategory) {
            "Science" -> {
                currentQuestion = "What planet is known as the Red Planet?"
                correctAnswer = "Mars"
            }
            "Math" -> {
                currentQuestion = "What is 5 + 7?"
                correctAnswer = "12"
            }
            "History" -> {
                currentQuestion = "Who was the first President of the USA?"
                correctAnswer = "George Washington"
            }
            else -> {
                currentQuestion = "Category not found."
                correctAnswer = ""
            }
        }
        resultTextView.text = currentQuestion
    }

    fun onSubmitAnswerClick(view: View) {
        val userAnswer = answerEditText.text.toString().trim()
        if (userAnswer.equals(correctAnswer, ignoreCase = true)) {
            resultTextView.text = "Correct! The answer was $correctAnswer."
        } else {
            resultTextView.text = "Wrong answer. The correct answer was $correctAnswer."
        }
    }
}
