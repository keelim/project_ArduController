package com.keelim.arducon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
                Log.d(TAG, "onClick: up");
                Toast.makeText(getApplication(), "up", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_down:
                Toast.makeText(getApplicationContext(), "down", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_left:
                Toast.makeText(getApplicationContext(), "left", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_right:
                Toast.makeText(getApplicationContext(), "right", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_center:
                Toast.makeText(getApplicationContext(), "center", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bluetooth_on:
                Toast.makeText(getApplicationContext(), "bluetooth on", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bluetooth_off:
                Toast.makeText(getApplicationContext(), "bluetooth off", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bluetooth_connecting:
                Toast.makeText(getApplicationContext(), "bluetooth connecting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bluetooth_senddata:
                Toast.makeText(getApplicationContext(), "bluetooth sendData", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}