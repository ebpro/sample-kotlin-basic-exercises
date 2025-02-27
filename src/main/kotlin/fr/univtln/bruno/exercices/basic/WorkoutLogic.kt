// 2. Control Flow
fun calculateIntensity(calories: Int): String = when {
    calories < 100 -> "Facile"
    calories <= 200 -> "Moyen"
    else -> "Difficile"
}

fun categorizeExercise(exerciseName: String): String = when (exerciseName.lowercase()) {
    in listOf("course", "vélo", "natation") -> "Cardio"
    in listOf("squat", "pompes", "tractions") -> "Force"
    in listOf("yoga", "étirements") -> "Flexibilité"
    else -> "Autre"
}

fun calculateWeeklyCalories(dailyWorkouts: List<Int>): Int = dailyWorkouts.sum()

fun displayExercises(exercises: List<Exercise>) {
    // For traditionnel
    for (exercise in exercises) {
        println("${exercise.name}: ${exercise.duration}min")
    }

    // forEach avec lambda
    exercises.forEach { exercise ->
        println("${exercise.name}: ${exercise.calories} calories")
    }

    // forEachIndexed
    exercises.forEachIndexed { index, exercise ->
        println("${index + 1}. ${exercise.name}")
    }

    // Index manuel
    for (i in exercises.indices) {
        val exercise = exercises[i]
        println("Exercise #${i + 1}: ${exercise.name}")
    }
}

class IntenseExerciseIterator(private val exercises: List<Exercise>) : Iterator<Exercise> {
    private var index = 0

    override fun hasNext(): Boolean {
        while (index < exercises.size) {
            if (exercises[index].calories > 200) return true
            index++
        }
        return false
    }

    override fun next(): Exercise {
        if (!hasNext()) throw NoSuchElementException()
        return exercises[index++]
    }
}

// Utilisation de séquences
fun exercisesByType(exercises: List<Exercise>, type: ExerciseType): Sequence<Exercise> = sequence {
    for (exercise in exercises) {
        if (exercise.type == type) {
            yield(exercise)
        }
    }
}

fun main() {
    println("Intensity for 150 calories: " + calculateIntensity(150))
    println("Exercise category for 'course': " + categorizeExercise("course"))
    exercisesByType(
        listOf(
            Exercise(ExerciseType.CARDIO, "course", 30, 150),
            Exercise(ExerciseType.STRENGTH, "pompes", 15, 100)
        ), ExerciseType.CARDIO
    ).forEach { println(it) }
    displayExercises(
        listOf(
            Exercise(ExerciseType.CARDIO, "course", 30, 150),
            Exercise(ExerciseType.STRENGTH, "pompes", 15, 100)
        )
    )
}