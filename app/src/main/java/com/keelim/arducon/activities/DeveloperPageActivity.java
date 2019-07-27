package com.keelim.arducon.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.keelim.arducon.R;
import com.keelim.arducon.utils.MaterialHelper;

public class DeveloperPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_page);
        MaterialHelper.with(this).init().loadAbout();
    }
}
