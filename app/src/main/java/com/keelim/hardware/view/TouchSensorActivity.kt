package com.keelim.hardware.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.view.customs.SingleTouchEventView

class TouchSensorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(SingleTouchEventView(this, null))

        Toast.makeText(this, "화면에 원하는 그림을 그려주세요", Toast.LENGTH_SHORT).show()
    }
}