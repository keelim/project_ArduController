package com.keelim.arducon

import android.app.Application
import androidx.preference.PreferenceManager
import com.google.android.gms.ads.MobileAds
import com.keelim.arducon.utils.AppOpenManager
import com.keelim.arducon.utils.ThemeHelper
import timber.log.Timber


class MyApplication : Application() {
    private lateinit var appOpenManager: AppOpenManager
    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) { }
        appOpenManager = AppOpenManager(this) // 콜드 부팅에서 복귀시 ad*/

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themePref = sharedPreferences.getString("themePref", ThemeHelper.DEFAULT_MODE)
        ThemeHelper.applyTheme(themePref!!)
    }
}
