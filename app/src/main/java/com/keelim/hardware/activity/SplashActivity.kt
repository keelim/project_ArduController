package com.keelim.hardware.activity

import android.content.Intent
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.keelim.hardware.R


class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        FirebaseAnalytics.getInstance(this)

        val timer = Thread(Runnable {
            try {

                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }
        })
        timer.start()

    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    override fun onBackPressed() {

    }


}
