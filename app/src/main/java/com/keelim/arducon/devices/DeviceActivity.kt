package com.keelim.arducon.devices

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.arducon.R

class DeviceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)
        Toast.makeText(this, "디바이스 목록 입니다.", Toast.LENGTH_SHORT).show()
    }

}




