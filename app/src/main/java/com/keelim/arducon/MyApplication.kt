package com.keelim.arducon

import android.app.Application
import com.keelim.arducon.error.ExceptionHandler

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setCrashHandler()

        MobileAds.initialize(this) {
            object : OnInitializationCompleteListener {
                override fun onInitializationComplete(p0: InitializationStatus?) {

                }
            }
        }

        appOpenManager = AppOpenManager(this) // 콜드 부팅에서 복귀시 ad
    }

    private fun setCrashHandler() {

        val defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { _, _ ->
            // Crashlytics에서 기본 handler를 호출하기 때문에 이중으로 호출되는것을 막기위해 빈 handler로 설정
        }

        val fabricExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(
                ExceptionHandler(
                        this,
                        defaultExceptionHandler!!,
                        fabricExceptionHandler!!
                )
        )
    }


}
