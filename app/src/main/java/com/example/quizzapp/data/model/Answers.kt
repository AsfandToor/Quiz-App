package com.example.quizzapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "answers_table"
        , foreignKeys = [ForeignKey(entity = Questions::class,
                parentColumns = ["questionId"],
                childColumns = ["questionId"],
                onDelete = CASCADE)])

data class Answers (
        @PrimaryKey(autoGenerate = true)
        val answerId: Int,
        val questionId: Int,
        val isTrue: Boolean,
        val text: String
        )