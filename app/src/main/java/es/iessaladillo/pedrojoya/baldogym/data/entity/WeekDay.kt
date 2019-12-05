package es.iessaladillo.pedrojoya.baldogym.data.entity

import androidx.annotation.StringRes
import es.iessaladillo.pedrojoya.baldogym.R
import java.util.*

enum class WeekDay(@StringRes val nameResId: Int) {
    MONDAY(R.string.schedule_monday),
    TUESDAY(R.string.schedule_tuesday),
    WEDNESDAY(R.string.schedule_wednesday),
    THURSDAY((R.string.schedule_thursday)),
    FRIDAY(R.string.schedule_friday),
    SATURDAY(R.string.schedule_saturday),
    SUNDAY((R.string.schedule_sunday));
}

fun getCurrentWeekDay(): WeekDay =
    when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> WeekDay.MONDAY
        Calendar.TUESDAY -> WeekDay.TUESDAY
        Calendar.WEDNESDAY -> WeekDay.WEDNESDAY
        Calendar.THURSDAY -> WeekDay.THURSDAY
        Calendar.FRIDAY -> WeekDay.FRIDAY
        Calendar.SATURDAY -> WeekDay.SATURDAY
        else -> WeekDay.SUNDAY
    }