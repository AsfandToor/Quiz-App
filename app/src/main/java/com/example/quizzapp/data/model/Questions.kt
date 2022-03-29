package com.example.quizzapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions_table")
data class Questions (
    @PrimaryKey(autoGenerate = true)
    val questionId:Int,
    val text:String
        )