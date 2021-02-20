package com.keelim.testing.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.common.toast
import com.keelim.testing.R

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AospSplashActivity : AppCompatActivity(R.layout.activity_aosp_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast("AOSP(Android Open Source Project) 개선 프로젝트 측정.")

        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            startActivity(Intent(this@AospSplashActivity, AospActivity::class.java))
            finishAffinity() //앱을 종료한다.
        }
    }
}