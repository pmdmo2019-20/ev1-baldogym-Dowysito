package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession

class TrainingSessionViewModel(private val repository: Repository, private val application: Application): ViewModel() {
    private val _session: MutableLiveData<TrainingSession> = MutableLiveData()
    val session: LiveData<TrainingSession>
        get() = _session


    fun searchSession(id:Long) {
       _session.value=repository.getSession(id)
    }

    fun joinSession(){
        repository.joinSession(_session.value!!)
        searchSession(session.value!!.id)
    }

    fun quitSession() {
        repository.quitSession(_session.value!!)
        searchSession(session.value!!.id)
    }
}