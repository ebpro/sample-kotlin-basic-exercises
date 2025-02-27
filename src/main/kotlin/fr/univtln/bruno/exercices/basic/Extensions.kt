// 4. Functions
fun List<Exercise>.averageCalories(): Double =
    if (isEmpty()) 0.0 else sumOf { it.calories }.toDouble() / size

fun createExercise(
    type: ExerciseType = ExerciseType.CARDIO,
    duration: Int = 30,
    intensity: String = "Moyen"
): Exercise = Exercise(
    type = type,
    name = "Default Exercise",
    duration = duration,
    calories = when (intensity) {
        "Facile" -> 50
        "Moyen" -> 150
        "Difficile" -> 250
        else -> 100
    }
)

fun filterExercises(exercises: List<Exercise>, predicate: (Exercise) -> Boolean): List<Exercise> =
    exercises.filter(predicate)

fun main() {
    val exercises = listOf(
        Exercise(ExerciseType.CARDIO, "course", 30, 150),
        Exercise(ExerciseType.STRENGTH, "pompes", 15, 100),
        Exercise(ExerciseType.FLEXIBILITY, "étirements", 10, 50),
        createExercise(intensity = "Difficile"),
        createExercise(duration = 45)
    )

    println("Average calories: ${exercises.averageCalories()}")
    filterExercises(exercises) { it.calories > 100 }.forEach { println(it) }
}