package com.keelim.hardware.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_vibration.*

class VibrationActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibration)

        val v1 = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        vib_bt1.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v1.vibrate(VibrationEffect.createOneShot(1500, VibrationEffect.DEFAULT_AMPLITUDE))
                v1.vibrate(VibrationEffect.createWaveform(longArrayOf(500, 1000, 500, 2000), -1))
            } else {
                v1.vibrate(400)
                v1.vibrate(longArrayOf(500, 1000, 500, 1000), -1)
            }


            Toast.makeText(this, "Your Vibration Working Very Well", Toast.LENGTH_SHORT).show()

        }
    }
}