package com.personaltrainer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.personaltrainer.R
import com.personaltrainer.R.id.text_exercise_name
import com.personaltrainer.model.Exercise

/**
 * Created by Honza on 19.02.2018.
 */
class ExerciseAdapter(context: Context) : ExtendedRecyclerViewAdapter<ExerciseAdapter.ViewHolder, Exercise>(context) {
    override fun onBindItem(holder: ViewHolder, position: Int) {
        holder.textViewExerciseName.text = items[position].name
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_exercise, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewExerciseName: TextView = view.findViewById(text_exercise_name)
    }
}