package com.keelim.arducon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

public class ConActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arducon);

        Toast.makeText(this, "컨트롤러가 작동합니다. ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //메뉴 바를 설정을 한다.
        getMenuInflater().inflate(R.menu.menu_controller, menu);
        return true;

    }

    
}
