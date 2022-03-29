package com.example.quizzapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quizzapp.data.model.Answers
import com.example.quizzapp.data.model.Questions
import com.example.quizzapp.data.model.Scores

@Dao
interface QuizDao {

    // Inserting Queries
    // Inserting Answer into Answers Table
    @Insert
    fun insert(answers: Answers)

    // Inserting Question into Questions Table
    @Insert
    fun insert(questions: Questions)

    // Inserting Score into Scores Table
    @Insert
    fun insert(scores: Scores)

    // Deleting Queries
    // Delete Answers
    @Query("DELETE FROM answers_table")
    fun clearAllAnswers()
    @Delete
    fun clearAnswer(answers: Answers)

    // Delete Questions
    @Query("DELETE FROM questions_table")
    fun clearAllQuestions()
    @Delete
    fun clearQuestion(questions: Questions)

    // Delete Scores
    @Query("DELETE FROM scores_table")
    fun clearAllScores()
    @Delete
    fun clearScore(scores: Scores)

    // Fetching Queries
    // Getting Questions
    @Query("SELECT * FROM questions_table ORDER BY questionId ASC")
    fun getAllQuestions():List<Questions>

    // Getting Answers
    @Query("SELECT * FROM answers_table ORDER BY questionId ASC")
    fun getAllAnswers():List<Answers>
    @Query("SELECT * FROM answers_table WHERE answers_table.questionId = :id")
    fun getQuestionsAnswers(id: Int):List<Answers>

    // Getting Scores
    @Query("SELECT * FROM scores_table ORDER BY score DESC")
    fun getAllScores():List<Scores>

}