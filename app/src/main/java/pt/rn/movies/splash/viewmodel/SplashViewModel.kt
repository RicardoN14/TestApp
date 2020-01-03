package pt.rn.movies.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pt.rn.movies.splash.viewmodel.entities.SplashViewState

class SplashViewModel : ViewModel() {

    val stateLiveData : LiveData<SplashViewState>
        get() = _stateLiveData

    private val _stateLiveData by lazy {
        MutableLiveData<SplashViewState>()
    }

    init {
        startSplash()
    }

    private fun startSplash() {
        viewModelScope.launch {
            _stateLiveData.value = SplashViewState.ShowSplash
            delay(SPLASH_DURATION)
            _stateLiveData.value = SplashViewState.FinishSplash
        }
    }

    companion object{
        private const val SPLASH_DURATION = 5000L
    }

}