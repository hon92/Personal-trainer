package com.personaltrainer.model

import android.media.Image

/**
 * Created by Honza on 17.02.2018.
 */

enum class ExerciseCategory {
    WEIGHT,
    LENGTH,
    COUNT,
    TIME,
    OWN_WEIGHT
}

data class Exercise(val name: String,
                    val description: String,
                    val category: ExerciseCategory,
                    val image: Image? = null)