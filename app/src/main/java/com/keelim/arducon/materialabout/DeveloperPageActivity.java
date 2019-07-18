package com.keelim.arducon.materialabout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.keelim.arducon.R;

public class DeveloperPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_page);
        Helper.with(this).init().loadAbout();
    }
}
