package pt.rn.movies.main.view

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import pt.rn.movies.R
import pt.rn.movies.arch.lifecyclecomponents.LoggingLifecycleAwareComponent
import pt.rn.movies.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private val dataBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
        }
    }

    private val navController by lazy {
        findNavController(R.id.activity_main_nav_host)
    }

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.moviesFragment,
                R.id.seriesFragment
            ),
            dataBinding.activityMainDrawerLayout
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoggingLifecycleAwareComponent.bindObserver(this)
        setupNavigationAndToolbar()
        setupToolbar()
    }

    private fun setupNavigationAndToolbar() {
        dataBinding.activityMainNavigationView.setupWithNavController(navController)
    }

    private fun setupToolbar() {
        dataBinding.activityMainToolbarContainer.toolbar.apply {
            setupWithNavController(navController, appBarConfiguration)
        }
    }

    override fun onBackPressed() {
        dataBinding.activityMainDrawerLayout.apply {
            if (isDrawerOpen(GravityCompat.START)) {
                closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        }
    }

}