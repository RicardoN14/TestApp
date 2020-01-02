package pt.rn.movies.arch.application

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pt.rn.movies.BuildConfig
import pt.rn.movies.splash.di.splashModule
import timber.log.Timber

object Initializers {

    fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun initKoinDependencyInjection(application: MoviesApplication) =
        startKoin {
            // Use Koin Android Logger
            androidLogger()
            androidContext(application)
            modules(splashModule)
        }

}