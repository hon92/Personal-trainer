package com.personaltrainer.model

/**
 * Created by Honza on 17.02.2018.
 */

data class ExerciseProperties(private val seriesCount: Int) {
    private var repCounts: IntArray = IntArray(seriesCount)
    private var restLengths: IntArray = IntArray(seriesCount)

    constructor(seriesCount: Int,
                repsCount: Int,
                restLength: Int) : this(seriesCount) {
        repCounts = IntArray(seriesCount, { repsCount })
        restLengths = IntArray(seriesCount, { restLength })
    }

    constructor(seriesCount: Int,
                repCounts: Array<Int>,
                restLengths: Array<Int>) : this(seriesCount) {

        if (repCounts.size != seriesCount) {
            throw RuntimeException("repCounts size must be equal to seriesCount")
        }

        if (restLengths.size != seriesCount) {
            throw RuntimeException("restLengths size must be equal to seriesCount")
        }

        this.repCounts = repCounts.toIntArray()
        this.restLengths = restLengths.toIntArray()
    }

}