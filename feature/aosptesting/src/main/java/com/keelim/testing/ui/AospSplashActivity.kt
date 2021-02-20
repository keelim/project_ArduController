package com.keelim.testing.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.common.toast
import com.keelim.testing.R
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AospSplashActivity : AppCompatActivity(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast("AOSP(Android Open Source Project) 개선 프로젝트 측정.")

        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            startActivity(Intent(this@AospSplashActivity, AospActivity::class.java))
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
            finish() //앱을 종료한다.
        }
    }
}