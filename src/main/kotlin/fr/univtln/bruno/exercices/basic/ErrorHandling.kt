package fr.univtln.bruno.exercices.basic

import fr.univtln.bruno.exercices.basic.inheritance.CardioExercise
import fr.univtln.bruno.exercices.basic.inheritance.Exercise
import kotlinx.coroutines.runBlocking

// Type Result for error handling
sealed class ExerciseResult<out T> {
    data class Success<T>(val data: T) : ExerciseResult<T>()
    data class Error(val exception: Exception) : ExerciseResult<Nothing>()
}

fun validateExercise(exercise: Exercise): ExerciseResult<Exercise> = when {
    exercise.duration < 0 -> ExerciseResult.Error(IllegalArgumentException("Duration cannot be negative"))
    exercise.name.isBlank() -> ExerciseResult.Error(IllegalArgumentException("Name cannot be blank"))
    else -> ExerciseResult.Success(exercise)
}

// Extension functions for ExerciseResult
fun <T> ExerciseResult<T>.onSuccess(action: (T) -> Unit): ExerciseResult<T> {
    if (this is ExerciseResult.Success) action(data)
    return this
}

fun <T> ExerciseResult<T>.onError(action: (Exception) -> Unit): ExerciseResult<T> {
    if (this is ExerciseResult.Error) action(exception)
    return this
}

// Define a Client interface
interface Client {
    suspend fun getExercise(id: Int): Exercise
}

// Implement the Client interface
class ApiClient : Client {
    override suspend fun getExercise(id: Int): Exercise {
        // Simulate an API call
        return CardioExercise("Running", 30, 10.0, 5)
    }
}

// API call with error handling
suspend fun safeApiCall(call: suspend () -> Exercise): ExerciseResult<Exercise> = try {
    ExerciseResult.Success(call())
} catch (e: Exception) {
    ExerciseResult.Error(e)
}

// Utilisation
suspend fun getExerciseSafely(client: Client, id: Int): ExerciseResult<Exercise> = safeApiCall {
    client.getExercise(id)
}

fun main(): Unit = runBlocking {
    val client = ApiClient()
    val exercise = CardioExercise("Running", 30, 10.0, 5)

    validateExercise(exercise)
        .onSuccess { println("Exercise is valid: $it") }
        .onError { println("Validation failed: ${it.message}") }

    getExerciseSafely(client, 1)
        .onSuccess { println("Retrieved exercise: $it") }
        .onError { println("API call failed: ${it.message}") }
}