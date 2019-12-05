package es.iessaladillo.pedrojoya.baldogym.data.entity

import androidx.annotation.DrawableRes

data class TrainingSession(
    val id: Long,
    val name: String,
    val weekDay: WeekDay,
    @DrawableRes val photoResId: Int,
    val description: String,
    val time: String,
    val trainer: String,
    val room: String,
    val participants: Int,
    val userJoined: Boolean
)