package com.keelim.arducon.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import com.keelim.arducon.R
import io.fabric.sdk.android.Fabric

class SplashActivity : AppCompatActivity() {
    //인트로 액티비티를 생성한다.
    private var handler: Handler = Handler()
    //인앱 업데이트 어디서 등록을 해야 하는가?
    var runnable = Runnable {
        //runable 작동을 하고 시작
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent) //인텐트를 넣어준다. intro -> main
        finish() //앱을 종료한다.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out) //애니메이션을 넣어준다.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        setContentView(R.layout.activity_intro)
        handler.postDelayed(runnable, 1000) //handler를 통하여 사용
    }

    override fun onBackPressed() { //back 키 눌렀을 때
        super.onBackPressed()
        handler.removeCallbacks(runnable)
    }
}