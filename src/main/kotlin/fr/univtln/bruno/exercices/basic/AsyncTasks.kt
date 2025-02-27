package fr.univtln.bruno.exercices.basic

import Exercise
import Workout
import kotlinx.coroutines.*

class WorkoutManager {
    suspend fun loadWorkoutHistory(): List<Workout> = withContext(Dispatchers.IO) {
        delay(1000) // Simulate network delay
        listOf(Workout(listOf()))
    }

    fun startExerciseTimer(durationMinutes: Int) = GlobalScope.launch {
        val totalSeconds = durationMinutes * 60
        println("Starting exercise timer for $durationMinutes minutes")
        for (seconds in totalSeconds downTo 0 step 30) {
            val minutes = seconds / 60
            val remainingSeconds = seconds % 60
            println("Temps restant: $minutes minutes $remainingSeconds seconds")
            delay(30000) // Wait 30 seconds
        }
        println("Ending Exercise timer")
    }

    suspend fun runParallelExercises(exercises: List<Exercise>) = coroutineScope {
        exercises.map { exercise ->
            async {
                println("Starting exercise: ${exercise.name}")
                delay(exercise.duration * 1000L) // Simulate exercise duration
                println("Finished exercise: ${exercise.name}")
                exercise.calories // Return calories burned
            }
        }.awaitAll() // Wait for all exercises to complete and return their results
    }
}

fun main() = runBlocking {
    val manager = WorkoutManager()

    // Launch a coroutine to load workout history
    val job = launch {
        val workoutHistory = manager.loadWorkoutHistory()
        println("Historique des entraînements: $workoutHistory")
    }

    // Perform other tasks concurrently
    repeat(5) { i ->
        println("Doing other work $i")
        delay(500) // Simulate doing other work
    }

    job.join() // Wait for the workout history to be loaded

    var timer = manager.startExerciseTimer(2)
    manager.runParallelExercises(
        listOf(
            Exercise(ExerciseType.CARDIO, "course", 30, 150),
            Exercise(ExerciseType.STRENGTH, "pompes", 15, 100),
            Exercise(ExerciseType.FLEXIBILITY, "étirements", 10, 50)
        )
    ).forEach { calories -> println("Calories brûlées: $calories") }

    timer.join() // Wait for the exercise timer to finish
}