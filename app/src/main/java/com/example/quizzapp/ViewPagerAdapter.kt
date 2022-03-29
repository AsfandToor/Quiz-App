package com.example.quizzapp

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.quizzapp.data.QuizDatabase
import com.example.quizzapp.data.model.Answers
import com.example.quizzapp.data.model.Questions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ViewPagerAdapter(
    var list: List<Questions>
): RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var answers: List<Answers>
        val quesState = itemView.findViewById<TextView>(R.id.quesStatement)
        val quesTitle = itemView.findViewById<TextView>(R.id.quesTitle)
        val radios = listOf<RadioButton>(
            itemView.findViewById<RadioButton>(R.id.radio1),
            itemView.findViewById<RadioButton>(R.id.radio2),
            itemView.findViewById<RadioButton>(R.id.radio3)
        )
        val database = QuizDatabase.getDatabase(itemView.context).quizDao()
        val customeScope = CoroutineScope(Dispatchers.IO)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.customeScope.async {
            holder.answers = holder.database.getQuestionsAnswers(list[position].questionId)
        }.invokeOnCompletion {
            for (i in 0..2) {
                holder.radios[i].isChecked = false
                holder.radios[i].setText(holder.answers[i].text)
            }
        }

        holder.quesState.setText(list[position].text)
        holder.quesTitle.setText("Question " + (position + 1))

        for (i in 0..2) {
            holder.radios[i].setOnClickListener {
                if (holder.answers[i].isTrue) {
                    var score = binding.score.text
                    score = (score.toString().toInt() + 10).toString()
                    binding.score.setText(score)
                }
                if (binding.viewPager.currentItem == list.size - 1) {
                    val myContext = it.context as Activity
                    myContext.finish()
                }

                binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}