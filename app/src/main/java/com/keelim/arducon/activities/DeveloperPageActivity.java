package com.keelim.arducon.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.keelim.arducon.R;
import com.keelim.arducon.databinding.ActivityDeveloperPageBinding;
import com.keelim.arducon.utils.MaterialHelper;

public class DeveloperPageActivity extends AppCompatActivity {
    ActivityDeveloperPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_developer_page);
        binding.setActivity(this);
        MaterialHelper.with(this).init().loadAbout();
    }
}
