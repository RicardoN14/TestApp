package pt.rn.movies.arch.lifecyclecomponents

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber

class LoggingLifecycleAwareComponent : LifecycleObserver {

    private lateinit var tag: String

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() = Timber.tag(tag).d("onCreate()")

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() = Timber.tag(tag).d("onResume()")

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() = Timber.tag(tag).d("onStart()")

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() = Timber.tag(tag).d("onPause()")

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() = Timber.tag(tag).d("onStop()")

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() = Timber.tag(tag).d("onDestroy()")

    companion object {
        fun bindObserver(activity: FragmentActivity): LoggingLifecycleAwareComponent {
            val loggingComponent = LoggingLifecycleAwareComponent()
            loggingComponent.tag = activity.javaClass.simpleName
            activity.lifecycle.addObserver(loggingComponent)
            return loggingComponent
        }

        fun bindObserver(fragment: Fragment): LoggingLifecycleAwareComponent {
            val loggingComponent = LoggingLifecycleAwareComponent()
            loggingComponent.tag = fragment.javaClass.simpleName
            fragment.lifecycle.addObserver(loggingComponent)
            return loggingComponent
        }
    }
}
