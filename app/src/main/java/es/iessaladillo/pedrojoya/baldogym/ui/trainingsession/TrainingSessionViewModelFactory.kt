package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.baldogym.data.Repository

class TrainingSessionViewModelFactory (val repository: Repository, val aplication: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrainingSessionViewModel(repository, aplication) as T
    }
}