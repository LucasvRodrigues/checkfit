package com.example.checkfit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(private val listener: ExerciseItemListener) :
    ListAdapter<Exercise, ExerciseAdapter.ExerciseViewHolder>(ExerciseDiffUtil()) {

    class ExerciseViewHolder(view: View, private val listener: ExerciseItemListener) :
        RecyclerView.ViewHolder(view) {
        private val container: LinearLayout = view.findViewById(R.id.container)
        private val name: TextView = view.findViewById(R.id.name)
        private val repetitions: TextView = view.findViewById(R.id.repetitions)
        private val weight: TextView = view.findViewById(R.id.weight)

        fun bind(exercise: Exercise) {
            name.text = exercise.name
            repetitions.text = "Repetições: ${exercise.repetition}"
            weight.text = "Carga: ${exercise.weight}"

            container.setOnClickListener {
                listener.onExerciseClick(exercise)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_item, parent, false)
        return ExerciseViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ExerciseDiffUtil : DiffUtil.ItemCallback<Exercise>() {

    override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem == newItem
    }
}

interface ExerciseItemListener {

    fun onExerciseClick(exercise: Exercise)
}