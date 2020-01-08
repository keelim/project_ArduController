package com.keelim.arducon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.keelim.arducon.R;

import io.fabric.sdk.android.Fabric;


public class SplashActivity extends AppCompatActivity { //인트로 액티비티를 생성한다.
    private Handler handler;

    //인앱 업데이트 어디서 등록을 해야 하는가?
    Runnable runnable = () -> { //runable 작동을 하고 시작
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent); //인텐트를 넣어준다. intro -> main
        finish(); //앱을 종료한다.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); //애니메이션을 넣어준다.
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_intro);
        handler = new Handler();
        handler.postDelayed(runnable, 1000); //handler를 통하여 사용
    }

    @Override
    public void onBackPressed() { //back 키 눌렀을 때
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }
}


