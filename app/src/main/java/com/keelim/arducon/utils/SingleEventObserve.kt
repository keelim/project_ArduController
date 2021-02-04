package com.keelim.arducon.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.keelim.common.Event

@MainThread
inline fun <T> LiveData<Event<T>>.eventObserver(
        owner: LifecycleOwner,
        crossinline onChagned: (T) -> Unit
): Observer<Event<T>>{
    val wrappedObserver = Observer<Event<T>> { t ->
        t.getContentIfNotHandled()?.let{
            onChagned.invoke(it)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}