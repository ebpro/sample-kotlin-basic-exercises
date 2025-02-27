package fr.univtln.bruno.exercices.basic

import Exercise
import ExerciseType

// 5. Collections
fun getLongExercises(exercises: List<Exercise>): List<Exercise> =
    exercises.filter { it.duration > 20 }

fun groupExercisesByType(exercises: List<Exercise>): Map<ExerciseType, List<Exercise>> =
    exercises.groupBy { it.type }

fun getMostIntenseExercise(exercises: List<Exercise>): Exercise? =
    exercises.maxByOrNull { it.calories }

fun main() {
    val exercises = listOf(
        Exercise(ExerciseType.CARDIO, "course", 30, 150),
        Exercise(ExerciseType.STRENGTH, "pompes", 15, 100),
        Exercise(ExerciseType.FLEXIBILITY, "étirements", 10, 50)
    )

    println("Long exercises:")
    getLongExercises(exercises).forEach { println(it) }

    println("Exercises by type:")
    groupExercisesByType(exercises).forEach { (type, list) ->
        println("$type: $list")
    }

    println("Most intense exercise:")
    println(getMostIntenseExercise(exercises))
}