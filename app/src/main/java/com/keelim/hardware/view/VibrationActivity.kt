package com.keelim.hardware.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_vibration.*

class VibrationActivity : AppCompatActivity(R.layout.activity_vibration) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val v1 = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        vib_bt1.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v1.vibrate(VibrationEffect.createOneShot(1500, VibrationEffect.DEFAULT_AMPLITUDE))
                v1.vibrate(VibrationEffect.createWaveform(longArrayOf(500, 1000, 500, 2000), -1))
                Toast.makeText(this, "화면이 진동하고 있습니다. ", Toast.LENGTH_SHORT).show()
            } else {
                v1.vibrate(400)
                v1.vibrate(longArrayOf(500, 1000, 500, 1000), -1)
            }
        }
    }
}