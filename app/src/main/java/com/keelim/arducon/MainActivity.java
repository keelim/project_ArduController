package com.keelim.arducon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button_change;


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
    public boolean onCreateOptionsMenu(Menu menu) {
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
