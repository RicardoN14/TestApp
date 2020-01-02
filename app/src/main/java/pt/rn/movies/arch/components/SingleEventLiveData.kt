package pt.rn.movies.arch.components

import android.util.Pair
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean

import androidx.annotation.MainThread
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 *
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 */
open class SingleEventLiveData<T> : MutableLiveData<T>() {

    private val pendingObservers = ConcurrentHashMap<Observer<T>, Pair<Observer<in T>, AtomicBoolean>>()

    override fun observe(@NonNull owner: LifecycleOwner, @NonNull observer: Observer<in T>) {
        val interceptor = object : Observer<T> {
            override fun onChanged(@Nullable t: T) {
                var observerToTrigger: Observer<in T>? = null
                synchronized(pendingObservers) {
                    if (pendingObservers.containsKey(this) && pendingObservers[this]!!.second.compareAndSet(true, false)) {
                        observerToTrigger = pendingObservers[this]!!.first
                    }
                }
                observerToTrigger?.onChanged(t)
            }
        }

        synchronized(pendingObservers) {
            pendingObservers.put(interceptor, Pair.create<Observer<in T>, AtomicBoolean>(observer, AtomicBoolean(false)))
        }

        // Observe the internal MutableLiveData
        super.observe(owner, interceptor)
    }

    override fun removeObserver(@NonNull observer: Observer<in T>) {
        super.removeObserver(observer)
        synchronized(pendingObservers) {
            for ((key, value) in pendingObservers) {
                if (observer === key || observer === value.first) {
                    pendingObservers.remove(key)
                    break
                }
            }
        }
    }

    @MainThread
    override fun setValue(@Nullable t: T?) {
        synchronized(pendingObservers) {
            for (pending in pendingObservers.values) {
                pending.second.set(true)
            }
        }
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }
}
