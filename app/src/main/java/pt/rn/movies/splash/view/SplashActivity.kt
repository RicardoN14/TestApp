package pt.rn.movies.splash.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import pt.rn.movies.R
import pt.rn.movies.arch.lifecyclecomponents.LoggingLifecycleAwareComponent
import pt.rn.movies.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private val dataBinding by lazy {
        DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
            .apply {
                lifecycleOwner = this@SplashActivity
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding // just to initialize it
        LoggingLifecycleAwareComponent.bindObserver(this)
    }

}