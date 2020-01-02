package pt.rn.movies.arch.extensions

import android.app.Activity
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun NavDirections.navigateTo(view: View) =
    Navigation.findNavController(view).navigate(this)

fun NavDirections.navigateToAndFinish(activity: Activity?, view: View) {
    navigateTo(view)
    activity?.finish()
}

fun navigateUp(view: View) =
    Navigation.findNavController(view).navigateUp()