package com.keelim.arducon;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.keelim.arducon.fragment.FragmentMain;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity { //fragment를 위한 액티비티 --> 추후 main으로 적용
    private DrawerLayout drawerLayout;
    private View drawerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                        Toast.makeText(SettingActivity.this, "현재 사용 중", Toast.LENGTH_SHORT).show();
                    case R.id.drawer_exit:
                        finish();
                        break;
                }
                return false;
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.main_fragment, new FragmentMain());
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(drawerView);
                break;
            case R.id.menu_setting:
                Toast.makeText(this, "현재 사용 중", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
