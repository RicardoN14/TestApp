package pt.rn.movies.arch.extensions

import android.animation.Animator
import android.view.ViewPropertyAnimator

fun ViewPropertyAnimator.setAnimationEndAction(endAction: () -> Unit): ViewPropertyAnimator{
    this.setListener(object: Animator.AnimatorListener{
        override fun onAnimationRepeat(p0: Animator?) {
        }

        override fun onAnimationEnd(p0: Animator?) {
            endAction()
        }

        override fun onAnimationCancel(p0: Animator?) {
        }

        override fun onAnimationStart(p0: Animator?) {
        }

    })

    return this
}