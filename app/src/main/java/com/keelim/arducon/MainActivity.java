package com.keelim.arducon;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button_change;

    private static final String TAG = "MainActivity";
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int MESSAGE_READ = 2;
    private static final int CONNECT_FAILE = 3;
    private static final int CONNECT_SUCESS = 4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_change = (Button) findViewById(R.id.button_change);

        button_change.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "화면 전환을 실행 합니다. ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ControllerActivity.class);
                startActivity(intent);

            }
        });


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


}
