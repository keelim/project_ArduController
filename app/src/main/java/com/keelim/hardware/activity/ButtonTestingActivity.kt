package com.keelim.hardware.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class ButtonTestingActivity : AppCompatActivity(), OnTouchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttontesting)
        val main = findViewById<FrameLayout>(R.id.main_view)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        val action = event.action
        return when (event.keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                if (action == KeyEvent.ACTION_UP) {
                    Toast.makeText(this, "PRESSED VOLUME UP BUTTON", Toast.LENGTH_SHORT).show()
                }
                true
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                if (action == KeyEvent.ACTION_DOWN) {
                    Toast.makeText(this, "PRESSED VOLUME DOWN BUTTON", Toast.LENGTH_SHORT).show() //TODO
                }
                true
            }
            else -> super.dispatchKeyEvent(event)
        }
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        // TODO Auto-generated method stub
        return false
    }

    companion object {
    }
}