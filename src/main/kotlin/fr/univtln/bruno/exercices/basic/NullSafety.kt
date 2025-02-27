package fr.univtln.bruno.exercices.basic

import Exercise

const val MIN_WORKOUT_DURATION = 30

// 7. Null Safety
fun getExerciseDuration(exercise: Exercise?): Int = exercise?.duration ?: MIN_WORKOUT_DURATION

fun getExerciseDetails(exercise: Exercise?): String {
    return exercise?.let {
        "Exercise: ${it.name}, Coach: ${it.coach ?: "Pas de coach"}"
    } ?: "Aucun exercice disponible"
}

fun main() {
    val exercise = Exercise(ExerciseType.CARDIO, "course", 30, 150)
    val nullExercise: Exercise? = null

    println("Duration: ${getExerciseDuration(exercise)}")
    println("Details: ${getExerciseDetails(exercise)}")
    println("Duration: ${getExerciseDuration(nullExercise)}")
    println("Details: ${getExerciseDetails(nullExercise)}")
}