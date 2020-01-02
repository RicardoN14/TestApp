package pt.rn.movies.splash.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import pt.rn.movies.splash.viewmodel.SplashViewModel

val splashModule: Module = module {
    viewModel { SplashViewModel() }
}