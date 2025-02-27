import fr.univtln.bruno.exercices.basic.inheritance.Exercise
import fr.univtln.bruno.exercices.basic.inheritance.CardioExercise
import fr.univtln.bruno.exercices.basic.inheritance.StrengthExercise

// 1. Container générique avec covariance
// T est covariant, on peut donc utiliser Container<StrengthExercise> là où Container<Exercise> est attendu
// Par exemple, si Container<StrengthExercise> hérite de Container<Exercise>, on peut utiliser Container<StrengthExercise> là où Container<Exercise> est attendu
// Cela permet de récupérer des objets de type StrengthExercise dans un container de type Exercise
interface Container<out T> {
    fun get(): T
}

// 2. Logger générique avec contravariance
// T est contravariant, on peut donc utiliser Logger<Exercise> là où Logger<StrengthExercise> est attendu
// Par exemple, si Logger<Exercise> hérite de Logger<StrengthExercise>, on peut utiliser Logger<Exercise> là où Logger<StrengthExercise> est attendu
// Cela permet de logger des objets de type Exercise dans un logger de type StrengthExercise
interface Logger<in T> {
    fun log(item: T)
}

// 3. Classe de gestion d'exercices générique
// T est un type générique qui hérite de Exercise
// On peut donc utiliser ExerciseManager<CardioExercise> ou ExerciseManager<StrengthExercise>
// Cela permet de gérer des exercices de type CardioExercise ou StrengthExercise
class ExerciseManager<T : Exercise> {
    private val exercises = mutableListOf<T>()

    fun add(exercise: T) {
        exercises.add(exercise)
    }

    fun getByType(predicate: (T) -> Boolean): List<T> =
        exercises.filter(predicate)
}

// 4. Classe avec contraintes multiples
// T est un type générique qui hérite de Exercise et Comparable<T>
// On peut donc utiliser WorkoutAnalyzer<CardioExercise> ou WorkoutAnalyzer<StrengthExercise>
// Cela permet d'analyser des exercices de type CardioExercise ou StrengthExercise
// La méthode findMostIntense renvoie l'exercice le plus intense en fonction de la comparaison
class WorkoutAnalyzer<T> where T : Exercise, T : Comparable<T> {
    fun findMostIntense(exercises: List<T>): T? =
        exercises.maxOrNull()
}

// 5. Extension function générique avec reified type
// Cette fonction d'extension permet de filtrer une liste d'exercices par type
// Par exemple, si on a une liste d'exercices de différents types, on peut filtrer les exercices de type CardioExercise
// inline permet d'utiliser le type reified T pour filtrer les éléments de la liste
// reified permet de récupérer le type de T à l'exécution
inline fun <reified T : Exercise> List<Exercise>.filterByType(): List<T> =
    filterIsInstance<T>()

// Programme de démonstration
fun main() {
    // Création d'instances
    val cardioManager = ExerciseManager<CardioExercise>()
    val strengthManager = ExerciseManager<StrengthExercise>()

    // Ajout d'exercices
    cardioManager.add(CardioExercise("Running", 30, 5.0, 8))
    strengthManager.add(StrengthExercise("Squats", 20, 12, 60))

    // Utilisation des filtres génériques
    // it est un paramètre implicite qui représente chaque élément de la liste
    val longCardio = cardioManager.getByType { it.duration > 20 }
    println("Long cardio exercises: $longCardio")

    // Utilisation de l'extension function avec type reified
    val exercises = listOf(
        CardioExercise("Running", 30, 5.0, 8),
        StrengthExercise("Squats", 20, 12, 60)
    )

    val cardioOnly = exercises.filterByType<CardioExercise>()
    println("Cardio exercises: $cardioOnly")
}
