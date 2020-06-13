package com.keelim.hardware.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.keelim.hardware.MainActivity
import com.keelim.hardware.R

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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



}