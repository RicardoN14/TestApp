package pt.rn.movies.arch.extensions

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.CollapsingToolbarLayout

fun FragmentActivity.setupToolbarWithUpNavigation(
    toolbar: Toolbar,
    @IdRes navControllerIdRsc: Int,
    @StringRes titleRes: Int? = null,
    title: String? = null
) {
    val navController = findNavController(navControllerIdRsc)

    val appBarConfiguration =
        AppBarConfiguration.Builder()
            .setFallbackOnNavigateUpListener { onNavigateUp() }
            .build()

    toolbar.apply {
        setupWithNavController(navController, appBarConfiguration)
        this.title = title ?: getString(titleRes!!)
    }
}

fun FragmentActivity.setupCollapsingToolbarWithUpNavigation(
    collapsingToolbar: CollapsingToolbarLayout,
    @IdRes navControllerIdRsc: Int,
    toolbar: Toolbar, @StringRes titleRes: Int? = null,
    title: String? = null
) {
    val navController = findNavController(navControllerIdRsc)

    val appBarConfiguration =
        AppBarConfiguration.Builder()
            .setFallbackOnNavigateUpListener { onNavigateUp() }
            .build()

    toolbar.apply {
        this.title = title ?: getString(titleRes!!)
    }

    collapsingToolbar.setupWithNavController(toolbar, navController, appBarConfiguration)
}

fun FragmentActivity.setupToolbarWithNavigationDrawer(
    toolbar: Toolbar,
    navController: NavController,
    drawerLayout: DrawerLayout,
    @StringRes titleRes: Int? = null,
    title: String? = null
) {
    val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

    toolbar.apply {
        setupWithNavController(navController, appBarConfiguration)
        this.title = title ?: getString(titleRes!!)
    }
}
