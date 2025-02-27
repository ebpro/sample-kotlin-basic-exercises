package fr.univtln.bruno.exercices.basic;

import Exercise
import UserProfile
import Workout

// 6. Scope Functions
fun configureWorkout(exercises: List<Exercise>) = with(Workout(exercises)) {
    println("Durée totale: ${totalDuration()} minutes")
    println("Calories totales: ${totalCalories()}")
    this
}

fun processOptionalExercise(exercise: Exercise?) = exercise?.let {
    "Exercise ${it.name} brûle ${it.calories} calories"
}

fun createUserProfile(name: String, age: Int) = UserProfile(name, age, 70.0f).apply {
    println("Profil créé pour $name")
}

fun main() {
    val exercises = listOf(
        Exercise(ExerciseType.CARDIO, "course", 30, 150),
        Exercise(ExerciseType.FLEXIBILITY, "étirements", 10, 50),
        Exercise(ExerciseType.STRENGTH, "pompes", 15, 100)
    )

    val workout = configureWorkout(exercises)
    val optionalExercise = processOptionalExercise(exercises.firstOrNull())
    val profile = createUserProfile("Alice", 30)

    println(workout)
    println(optionalExercise)
    println(profile)
}