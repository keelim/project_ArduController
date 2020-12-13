package com.keelim.arducon

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.keelim.arducon.utils.AppOpenManager

class MyApplication : Application() {
    private lateinit var appOpenManager: AppOpenManager
    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) { }
        appOpenManager = AppOpenManager(this) // 콜드 부팅에서 복귀시 ad*/
    }
}
