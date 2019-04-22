package com.keelim.arducon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class IntroActivity extends AppCompatActivity { //인트로 액티비티를 생성한다.
    private Handler handler;

    Runnable runnable = new Runnable() {
        @Override
        public void run() { //runable 작동을 하고 시작
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        init();

        handler.postDelayed(runnable, 1000); //handler
    }

    public void init() { //초기 이벤트 설정
        handler = new Handler();
    }

    @Override
    public void onBackPressed() { //back 키 눌렀을 때
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }
}


