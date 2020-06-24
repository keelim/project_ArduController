package com.keelim.hardware.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.keelim.hardware.MyApplication;

public class BaseActivity extends AppCompatActivity {
    public void progressON() {
        MyApplication.getInstance().progressON(this, null);
    }

    public void progressON(String message) {
        MyApplication.getInstance().progressON(this, message);
    }

    public void progressOFF() {
        MyApplication.getInstance().progressOFF();
    }
}

