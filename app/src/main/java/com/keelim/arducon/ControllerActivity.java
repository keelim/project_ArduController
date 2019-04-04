package com.keelim.arducon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

public class ControllerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 메뉴를 선택할 수 있게 한다.
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }
}
