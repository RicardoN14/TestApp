package pt.rn.movies.arch.extensions

import android.view.View

fun View.rotateForEver(duration: Long = 2500) {
    this.animate().rotationBy(360f).setDuration(duration).setAnimationEndAction {
        rotateForEver()
    }.start()
}