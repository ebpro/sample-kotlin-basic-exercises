package fr.univtln.bruno.exercices.basic.inheritance

interface Exercise {
    val name: String
    val duration: Int
    fun calculateCalories(): Int
}

abstract class AbstractExercise : Exercise {
    abstract override val name: String
    abstract override val duration: Int
    abstract override fun calculateCalories(): Int

    open fun describe(): String = "$name (${duration}min)"
}

class CardioExercise(
    override val name: String,
    override val duration: Int,
    val distance: Double,
    val intensity: Int
) : AbstractExercise() {
    override fun calculateCalories(): Int = (duration * intensity * 3.5).toInt()

    override fun describe(): String = "${super.describe()} - ${distance}km"
}

class StrengthExercise(
    override val name: String,
    override val duration: Int,
    val repetitions: Int,
    val weight: Int
) : AbstractExercise() {
    override fun calculateCalories(): Int = (duration * weight * 2.1).toInt()

    override fun describe(): String = "${super.describe()} - ${repetitions}x${weight}kg"
}

interface ExerciseTracker {
    fun startExercise()

    fun pauseExercise()

    fun stopExercise()

    fun getCurrentStatus() : String
}

class BasicExerciseTracker : ExerciseTracker {
    private var status: String = "Not started"

    override fun startExercise() {
        status = "Started"
    }

    override fun pauseExercise() {
        status = "Paused"
    }

    override fun stopExercise() {
        status = "Stopped"
    }

    override fun getCurrentStatus(): String {
        return status
    }
}

class SmartTracker(
    private val exercise: AbstractExercise,
    private val tracker: ExerciseTracker
) : Exercise by exercise, ExerciseTracker by tracker

fun main() {
    val cardio = CardioExercise("Running", 30, 10.0, 10)

    val basicTracker = BasicExerciseTracker()
    val smartCardio = SmartTracker(cardio, basicTracker)

    smartCardio.startExercise()
    println(smartCardio.getCurrentStatus())
    smartCardio.stopExercise()
    println(smartCardio.getCurrentStatus())

    println(smartCardio.calculateCalories())

}