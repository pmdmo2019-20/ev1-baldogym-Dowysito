package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay
import kotlinx.android.synthetic.main.schedule_activity.*

class ScheduleActivityViewModel(private val repository: Repository, private val application: Application): ViewModel() {


    private val _currentDayOptionSelected: MutableLiveData<WeekDay> =
        MutableLiveData(getCurrentWeekDay())
    val currentDayOptionSelected: LiveData<WeekDay>
        get() = _currentDayOptionSelected

    private val _sessions: MutableLiveData<List<TrainingSession>> = MutableLiveData(repository.querydayList(_currentDayOptionSelected.value!!))
    val sessions: LiveData<List<TrainingSession>>
        get() = _sessions

    fun changeDay(day: WeekDay) {
        _currentDayOptionSelected.value = day
        _sessions.value=repository.querydayList(day)
    }

    fun joinSession(trainingSession: TrainingSession) {
        repository.joinSession(trainingSession)
        querylist()
    }

    fun quitSession(trainingSession: TrainingSession) {
        repository.quitSession(trainingSession)
        querylist()
    }

    private fun querylist() {
        _sessions.value=repository.querydayList(_currentDayOptionSelected.value!!)
    }


}
