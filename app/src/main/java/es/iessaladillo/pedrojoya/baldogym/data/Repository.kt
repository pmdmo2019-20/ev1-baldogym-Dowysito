package es.iessaladillo.pedrojoya.baldogym.data

import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay

interface Repository {

    // TODO

    fun createWeekSchedule(): List<TrainingSession>

    fun querydayList(day: WeekDay): List<TrainingSession>
    fun joinSession(trainingSession: TrainingSession)

}