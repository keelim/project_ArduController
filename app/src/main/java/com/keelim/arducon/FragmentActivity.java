package com.keelim.arducon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class FragmentActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    View drawerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentmain);
        //Navigation View
        drawerLayout = findViewById(R.id.drawerlayout);
        drawerView = findViewById(R.id.drawer);
        //Navigation View
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.drawer_account:
                        Toast.makeText(getApplicationContext(), "계정 창 준비 중입니다. ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_bug_report:
                        Intent intent_bugReport = new Intent(getApplicationContext(), WebViewActivity.class);
                        startActivity(intent_bugReport); //webView page 이동을 할 것
                        break;
                    case R.id.drawer_setting:
                        Toast.makeText(getApplicationContext(), "설정 창 준비 중입니다. ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_exit:
                        finish();
                        break;
                }
                return false;
            }
        });
    }
}
