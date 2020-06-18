package com.keelim.hardware.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TouchSensorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(SingleTouchEventView(this, null))
    }
}