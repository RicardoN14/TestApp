package pt.rn.movies.splash.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pt.rn.movies.splash.viewmodel.entities.SplashViewState

class SplashViewModel : ViewModel() {

    val stateLiveData by lazy {
        MutableLiveData<SplashViewState>()
    }

    init {
        startSplash()
    }

    private fun startSplash() {
        viewModelScope.launch {
            stateLiveData.value = SplashViewState.ShowSplash
            delay(SPLASH_DURATION)
            stateLiveData.value = SplashViewState.FinishSplash
        }
    }

    companion object{
        private const val SPLASH_DURATION = 5000L
    }

}