package com.keelim.hardware.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.analytics.FirebaseAnalytics
import com.keelim.hardware.R


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

        Handler(Looper.getMainLooper()).postDelayed(runnable, 500)
    }

    override fun onBackPressed() {

    }
}
