package com.keelim.hardware.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.analytics.FirebaseAnalytics
import com.keelim.hardware.BuildConfig
import com.keelim.hardware.R
import com.keelim.hardware.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity() {
    private val runnable = Runnable {
        startActivity(Intent(this, MainActivity::class.java))
        finish() //앱을 종료한다.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out) //애니메이션을 넣어준다.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        FirebaseAnalytics.getInstance(this)

        splash_tv2.text = BuildConfig.VERSION_NAME
        Handler(Looper.getMainLooper()).postDelayed(runnable, 500)
    }

    override fun onBackPressed() {

    }
}
