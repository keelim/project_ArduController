package com.keelim.common

import androidx.lifecycle.Observer

open class Event<out T> (private val content: T) {
    var flag = false

    fun getContentIfNotHandled(): T? {
        return if (flag) null
        else {
            flag = true
            content
        }
    }

    fun peekContent(): T = content

    class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
        override fun onChanged(t: Event<T>?) {
            t?.getContentIfNotHandled()?.let { value ->
                onEventUnhandledContent(value)
            }
        }
    }

}