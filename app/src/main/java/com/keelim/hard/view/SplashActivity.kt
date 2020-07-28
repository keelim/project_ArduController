package com.keelim.hard.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hard.MyApplication
import com.keelim.hard.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        MyApplication.getInstance().progressON(this, "Thank you for your attending ")

        Handler(Looper.myLooper()!!).postDelayed( {
            MyApplication.getInstance().progressOFF()
        }, 3000)

        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }
}