package pt.rn.movies.main.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import pt.rn.movies.R
import pt.rn.movies.arch.lifecyclecomponents.LoggingLifecycleAwareComponent
import pt.rn.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val dataBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        LoggingLifecycleAwareComponent.bindObserver(this)
    }

}