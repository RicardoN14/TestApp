package pt.rn.movies.splash.viewmodel.entities

sealed class SplashViewState {

    object ShowSplash: SplashViewState()
    object FinishSplash: SplashViewState()

}