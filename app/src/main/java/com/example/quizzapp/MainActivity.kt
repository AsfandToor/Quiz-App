package com.example.quizzapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.quizzapp.data.QuizDatabase
import com.example.quizzapp.data.model.Answers
import com.example.quizzapp.data.model.Questions
import com.example.quizzapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            val intent = Intent(applicationContext, questions_act::class.java)
            startActivity(intent)
        }

        binding.highScoreBtn.setOnClickListener {
            val intent = Intent(this, highscore::class.java)
            startActivity(intent)
        }
    }
}