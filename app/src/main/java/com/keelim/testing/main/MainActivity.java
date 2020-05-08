package com.keelim.testing.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.keelim.testing.R;
import com.keelim.testing.test1.Test1Activity;
import com.keelim.testing.test2.Test2Activity;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(@NotNull View v) {
        int id = v.getId();

        switch (id){
            case R.id.btn_test1:
                Intent intent = new Intent(this, Test1Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_test2:
                Intent intent2 = new Intent(this, Test2Activity.class);
                startActivity(intent2);
                break;
        }
    }
}
