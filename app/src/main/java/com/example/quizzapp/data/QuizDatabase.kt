package com.example.quizzapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizzapp.data.model.Answers
import com.example.quizzapp.data.model.Questions
import com.example.quizzapp.data.model.Scores

@Database(entities = [Answers::class, Questions::class, Scores::class], version = 1)
abstract class QuizDatabase:RoomDatabase() {
    abstract fun quizDao():QuizDao

    companion object {
        @Volatile
        private var quizDatabase:QuizDatabase ?= null
        fun getDatabase(context: Context):QuizDatabase {
            val tempInstance = quizDatabase
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quizz_database"
                ).build()
                quizDatabase = instance
                return instance
            }
        }
    }
}