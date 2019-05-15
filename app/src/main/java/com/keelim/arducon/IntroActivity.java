package com.keelim.arducon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;


public class IntroActivity extends AppCompatActivity { //인트로 액티비티를 생성한다. //manifest  등록
    private Handler handler;

    //인앱 업데이트 어디서 등록을 해야 하는가?

    Runnable runnable = new Runnable() {
        @Override
        public void run() { //runable 작동을 하고 시작
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent); //인텐트를 넣어준다. intro -> main
            finish(); //앱을 종료한다.
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right); //애니메이션을 넣어준다.
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate
        inAppUpdate();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        handler = new Handler();

        handler.postDelayed(runnable, 1000); //handler를 통하여 사용
    }

    private void inAppUpdate() {
    }

    @Override
    public void onBackPressed() { //back 키 눌렀을 때
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }
}


