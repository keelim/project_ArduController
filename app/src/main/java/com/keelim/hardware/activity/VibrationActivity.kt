package com.keelim.hardware.activity

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class VibrationActivity : AppCompatActivity() {
    private lateinit var b1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibration)
        b1 = findViewById(R.id.but101)
        b1.setOnClickListener { v: View? ->
            val v1 = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            v1.vibrate(400)
            Toast.makeText(this, "Your Vibration Working Very Well", Toast.LENGTH_SHORT).show()
        }
    }
}