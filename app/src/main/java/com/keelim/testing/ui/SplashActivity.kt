package com.keelim.testing.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.keelim.testing.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loading()
        Snackbar.make(splash_container, "AOSP(Android Open Source Project) 개선 프로젝트 측정.", Snackbar.LENGTH_SHORT).show()
    }

    private fun loading() {
        Handler(Looper.getMainLooper()).postDelayed(
                {
                    Intent(this@SplashActivity, MainActivity::class.java).apply {
                        startActivity(this)
                        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
                        finish()
                    }
                }, 3000
        )
    }

    override fun onBackPressed() {} //
}