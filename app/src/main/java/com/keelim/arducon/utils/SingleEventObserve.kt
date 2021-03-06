//package com.keelim.arducon.utils
//
//import androidx.annotation.MainThread
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.Observer
//import com.keelim.common.com.keelim.common.com.keelim.benchmark.data.Event
//
//@MainThread
//inline fun <T> LiveData<com.keelim.common.com.keelim.benchmark.data.Event<T>>.eventObserver(
//        owner: LifecycleOwner,
//        crossinline onChagned: (T) -> Unit
//): Observer<com.keelim.common.com.keelim.benchmark.data.Event<T>>{
//    val wrappedObserver = Observer<com.keelim.common.com.keelim.benchmark.data.Event<T>> { t ->
//        t.getContentIfNotHandled()?.let{
//            onChagned.invoke(it)
//        }
//    }
//    observe(owner, wrappedObserver)
//    return wrappedObserver
//}