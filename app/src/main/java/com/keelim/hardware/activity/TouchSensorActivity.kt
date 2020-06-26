package com.keelim.hardware.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.activity.customs.SingleTouchEventView

class TouchSensorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(SingleTouchEventView(this, null))
    }
}