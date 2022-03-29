package com.example.quizzapp

import android.content.DialogInterface
import android.content.Intent
import android.gesture.Gesture
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.quizzapp.data.QuizDao
import com.example.quizzapp.data.QuizDatabase
import com.example.quizzapp.data.model.Questions
import com.example.quizzapp.data.model.Scores
import com.example.quizzapp.databinding.ActivityMainBinding
import com.example.quizzapp.databinding.ActivityQuestionsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
lateinit var binding: ActivityQuestionsBinding


class questions_act : AppCompatActivity() {
    lateinit var questions:List<Questions>
    lateinit var customeScope:CoroutineScope
    lateinit var input:EditText
    lateinit var database: QuizDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
            .setTitle("Choose your name")
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i -> })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->  })
            .setView(input)
            .create()
            .show()

        database = QuizDatabase.getDatabase(this).quizDao()
        customeScope = CoroutineScope(Dispatchers.IO)

        binding.viewPager.isUserInputEnabled = false

        customeScope.async {
            questions = database.getAllQuestions()
        }.invokeOnCompletion {
            val adapter = ViewPagerAdapter(questions)
            binding.viewPager.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (binding.viewPager.currentItem == questions.size - 1) {
            customeScope.async {
                database.insert(Scores(0, input.text.toString(), binding.score.text.toString().toInt()))
            }
        }
        val intent = Intent(this, highscore::class.java)
        startActivity(intent)
    }
}