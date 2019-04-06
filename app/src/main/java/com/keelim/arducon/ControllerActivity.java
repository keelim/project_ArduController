package com.keelim.arducon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        switch (id) {
            case R.id.action_bluetooth:
                intent = new Intent(getApplicationContext(), MainActivity.class); //todo bluetooth
                startActivity(intent);
                break;
            case R.id.action_main:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.action_controller:
                intent = new Intent(getApplicationContext(), ControllerActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }

        return true;
    }
}
