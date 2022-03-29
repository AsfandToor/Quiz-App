package com.example.quizzapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzapp.data.QuizDatabase
import com.example.quizzapp.data.model.Scores
import com.example.quizzapp.databinding.ActivityHighscoreBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class highscore : AppCompatActivity() {
    lateinit var binding: ActivityHighscoreBinding
    lateinit var scores:List<Scores>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighscoreBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        val database = QuizDatabase.getDatabase(this).quizDao()
        val customeScope = CoroutineScope(Dispatchers.IO)

        customeScope.async {
            scores = database.getAllScores()
        }.invokeOnCompletion {
            val adapter = ScoreAdapter(scores)
            binding.scoresRv.adapter = adapter
            binding.scoresRv.layoutManager = LinearLayoutManager(applicationContext)
        }
    }
}