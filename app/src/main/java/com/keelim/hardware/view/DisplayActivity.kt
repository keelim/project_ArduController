package com.keelim.hardware.view

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_display.*

class DisplayActivity : AppCompatActivity(R.layout.activity_display) {
    private lateinit var a: IntArray

    override fun onCreate(savedInstanceState: Bundle?) { //TODO 색까를 좀더 다양하게 사용하기
        super.onCreate(savedInstanceState)

        a = IntArray(10).apply {
            this[0] = Color.parseColor("#f4c2c6")
            this[1] = Color.parseColor("#c7afce")
            this[2] = Color.parseColor("#fbcab3")
            this[3] = Color.parseColor("#ffe8f4")
            this[4] = Color.parseColor("#525252")
            this[5] = Color.parseColor("#bcdbbe")
            this[6] = Color.parseColor("#1ca589")
            this[7] = Color.parseColor("#c1073f")
            this[8] = Color.parseColor("#edcdc3")
            this[9] = Color.parseColor("#ecffef")
        }

        Snackbar.make(display_container, "화면을 터치해주세요", Snackbar.LENGTH_SHORT).show()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = (Math.random() * 10).toInt()
        display_layout!!.setBackgroundColor(a[x])
        return false
    }
}