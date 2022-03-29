package com.example.quizzapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzapp.data.model.Scores

class ScoreAdapter(
    val list:List<Scores>
) :RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {
    class ScoreViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.scoreName)
        val score = itemView.findViewById<TextView>(R.id.scoreInt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scores_layout, parent, false)
        return ScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.title.setText(list[position].name.toString())
        holder.score.setText(list[position].score.toString())
    }

    override fun getItemCount(): Int {
        return list.size
    }
}