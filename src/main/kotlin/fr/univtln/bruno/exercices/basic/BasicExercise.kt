package fr.univtln.bruno.exercices.basic

// Constants
const val DEFAULT_DURATION = 15
const val DEFAULT_CALORIES = 150

// Basic Exercise class for introduction
class BasicExercise(
    val name: String = "Push-ups",    // non-modifiable with default value
    var duration: Int = DEFAULT_DURATION,           // modifiable with default value
    var caloriesBurned: Int = DEFAULT_CALORIES     // modifiable with default value
) {
    // Validation in init block
    init {
        require(duration > 0) { "Duration must be positive" }
        require(caloriesBurned >= 0) { "Calories burned cannot be negative" }
    }

    override fun toString(): String =
        "Exercise(name=$name, duration=$duration min, calories=$caloriesBurned)"
}

fun main() {
    // Create instances with different constructors
    val defaultExercise = BasicExercise()
    val customExercise = BasicExercise("Squats", 30, 200)

    // Print exercises using toString()
    println(defaultExercise)
    println(customExercise)

    // Demonstrate property modification
    customExercise.duration += 10
    println("Updated exercise: $customExercise")
}