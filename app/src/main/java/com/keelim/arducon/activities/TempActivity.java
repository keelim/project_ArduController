package com.keelim.arducon.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.keelim.arducon.R;
import com.keelim.arducon.fragments.SettingFragmnet;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //fragment 설정을 위한 activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SettingFragmnet())
                .commit();

    }
}
