package com.example.checkfit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ExerciseItemListener {

    private val exerciseList = mutableListOf<Exercise>()

    private val adapter = ExerciseAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val recyclerViewExercise = findViewById<RecyclerView>(R.id.recycler_view_exercise)
        val btnAdd = findViewById<Button>(R.id.btn_add)
        val etName = findViewById<EditText>(R.id.et_name)
        val etRepetition = findViewById<EditText>(R.id.et_repetitions)
        val etWeight = findViewById<EditText>(R.id.et_weight)

        recyclerViewExercise.adapter = adapter
        adapter.submitList(exerciseList)

        btnAdd.setOnClickListener {
            val exerciseName = etName.text.toString()
            val exerciseRepetitions = etRepetition.text.toString()
            val exerciseWeight = etWeight.text.toString()

            if (exerciseName.isNotEmpty() && exerciseRepetitions.isNotEmpty() && exerciseWeight.isNotEmpty()) {
                val exercise = Exercise(
                    exerciseName,
                    exerciseRepetitions,
                    exerciseWeight
                )

                exerciseList.add(exercise)
                adapter.submitList(exerciseList)
                adapter.notifyDataSetChanged()

                etName.setText("")
                etRepetition.setText("")
                etWeight.setText("")
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onExerciseClick(exercise: Exercise) {
        AlertDialog.Builder(this)
            .setTitle(exercise.name)
            .setMessage("Deseja remover o exercicio?")
            .setPositiveButton(
                "Sim"
            ) { _, _ ->
                exerciseList.remove(exercise)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Não") { _, _ -> }
            .create().show()
    }
}