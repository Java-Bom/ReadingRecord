package chapter6.item39

import kotlin.properties.Delegates

sealed class WorkoutState

class PrepareState(val exercise: Exercise): WorkoutState()

class ExerciseState(val exercise: Exercise): WorkoutState()

object DoneState : WorkoutState()

fun List<Exercise>.toStates() : List<WorkoutState> =
    flatMap { exercise ->
        listOf(PrepareState(exercise), ExerciseState(exercise))
    } + DoneState

class Exercise