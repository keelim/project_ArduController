package com.keelim.arducon.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.keelim.arducon.R;
import com.keelim.arducon.databinding.ActivitySettingBinding;
import com.keelim.arducon.fragments.SettingFragment;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //fragment 설정을 위한 activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SettingFragment())
                .commit();
    }
}
