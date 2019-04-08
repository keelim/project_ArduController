package com.keelim.arducon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ControllerActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "onCLick";

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
            case R.id.action_main:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(this, "화면 전환을 실행 합니다.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
            case R.id.action_controller:
                intent = new Intent(getApplicationContext(), ControllerActivity.class);
                Toast.makeText(this, "화면 전환을 실행 합니다. ", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_up:
                break;
            case R.id.button_down:
                break;
            case R.id.button_left:
                break;
            case R.id.button_right:
                break;
            case R.id.button_center:
                break;
            case R.id.bluetooth_on:
                break;
            case R.id.bluetooth_off:
                break;
            case R.id.bluetooth_connecting:
                break;
            case R.id.bluetooth_senddata:
                break;
        }
    }
}