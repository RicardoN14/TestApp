package pt.rn.movies.arch.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import pt.rn.movies.arch.extensions.rotateForEver

object ViewBindingAdapters {

    @BindingAdapter("rotateForEver")
    @JvmStatic
    fun rotateForEver(view: View, rotate: Boolean) {
        if (rotate) {
            view.rotateForEver()
        } else {
            view.animate().setListener(null).cancel()
        }
    }

}