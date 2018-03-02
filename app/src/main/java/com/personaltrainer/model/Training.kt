package com.personaltrainer.model

/**
 * Created by Honza on 17.02.2018.
 */

data class ExerciseItem(val exercise: Exercise, val exerciseProperty: ExerciseProperties)

data class Training(val name: String) {
    private val exerciseItems: MutableList<ExerciseItem> = mutableListOf()

    fun addExercise(exercise: Exercise, exerciseProperty: ExerciseProperties) {
        exerciseItems.add(ExerciseItem(exercise, exerciseProperty))
    }
}