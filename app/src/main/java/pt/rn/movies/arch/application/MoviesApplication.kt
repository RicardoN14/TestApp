package pt.rn.movies.arch.application

import android.app.Application

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Initializers.apply {
            initTimber()
            initKoinDependencyInjection(this@MoviesApplication)
        }
    }

}