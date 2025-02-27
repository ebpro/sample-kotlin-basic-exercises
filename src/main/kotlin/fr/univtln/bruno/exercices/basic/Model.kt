// 3. Classes

import java.time.LocalDateTime

enum class ExerciseType {
    CARDIO, STRENGTH, FLEXIBILITY
}

// Main Exercise data class
data class Exercise(
    val type: ExerciseType,
    val name: String,
    val duration: Int,
    val calories: Int,
    val date: LocalDateTime = LocalDateTime.now(),
    val coach: String? = null
)

data class Workout(private val exercises: List<Exercise>) {
    fun totalDuration(): Int = exercises.sumOf { it.duration }
    fun totalCalories(): Int = exercises.sumOf { it.calories }
}

data class UserProfile(val name: String, val age: Int, val weight: Float)

data class User(
    val profile: UserProfile,
    private val workouts: MutableList<Workout> = mutableListOf()
) {
    fun addWorkout(workout: Workout) = workouts.add(workout)
    fun getWorkoutHistory(): List<Workout> = workouts.toList()
}

fun main() {
    // Create instances with different constructors
    val defaultExercise = Exercise(ExerciseType.CARDIO, "Push-ups", 15, 150)
    val customExercise = Exercise(ExerciseType.STRENGTH, "Squats", 30, 200, coach = "Bob")

    // Print exercises using toString()
    println(defaultExercise)
    println(customExercise)

    // Demonstrate property modification
    val updatedExercise = customExercise.copy(duration = customExercise.duration + 10)
    println("Updated exercise: $updatedExercise")

    // Create a workout
    val workout = Workout(listOf(defaultExercise, customExercise))
    println("Total duration: ${workout.totalDuration()} min")
    println("Total calories: ${workout.totalCalories()} cal")

    // Create a user
    val user = User(UserProfile("Alice", 30, 65.5f))
    user.addWorkout(workout)
    println("Workout history: ${user.getWorkoutHistory()}")
}