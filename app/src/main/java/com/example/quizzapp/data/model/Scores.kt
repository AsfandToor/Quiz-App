package com.example.quizzapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores_table")
data class Scores (
    @PrimaryKey(autoGenerate = true)
    val scoreId:Int,
    val name:String,
    val score:Int
        )