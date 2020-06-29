package com.keelim.hardware.activity

import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class SoundButtonActivity : AppCompatActivity(R.layout.activity_soundbutton) {

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        val action = event.action
        return when (event.keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                if (action == KeyEvent.ACTION_UP) {
                    Toast.makeText(this, "볼륨 업 버튼이 정상 작동 합니다.", Toast.LENGTH_SHORT).show()
                }
                true
            }

            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                if (action == KeyEvent.ACTION_DOWN) {
                    Toast.makeText(this, "볼륨 다운 버튼이 정상 작동 합니다.", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.dispatchKeyEvent(event)
        }
    }

}