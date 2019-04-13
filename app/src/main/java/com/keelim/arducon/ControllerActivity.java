package com.keelim.arducon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ControllerActivity extends AppCompatActivity {
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

    } //actionbar inflater

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        switch (id) {

            case R.id.action_controller:
                intent = new Intent(getApplicationContext(), ControllerActivity.class);
                Toast.makeText(this, "화면 전환을 실행 합니다. ", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;

        }
        return true;

    }
}
