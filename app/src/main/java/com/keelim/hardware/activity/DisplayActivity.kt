package com.keelim.hardware.activity

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class DisplayActivity : AppCompatActivity() {
    lateinit var a: IntArray
    var r: RelativeLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        r = findViewById(R.id.rr)
        a = IntArray(10)
        a[0] = Color.parseColor("#f4c2c6")
        a[1] = Color.parseColor("#c7afce")
        a[2] = Color.parseColor("#fbcab3")
        a[3] = Color.parseColor("#ffe8f4")
        a[4] = Color.parseColor("#525252")
        a[5] = Color.parseColor("#bcdbbe")
        a[6] = Color.parseColor("#1ca589")
        a[7] = Color.parseColor("#c1073f")
        a[8] = Color.parseColor("#edcdc3")
        a[9] = Color.parseColor("#ecffef")
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = (Math.random() * 10).toInt()
        r!!.setBackgroundColor(a[x])
        return false
    }
}