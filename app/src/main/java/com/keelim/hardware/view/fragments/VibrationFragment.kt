package com.keelim.hardware.view.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast

import androidx.fragment.app.Fragment
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_vibration.*

class VibrationFragment : Fragment(R.layout.activity_vibration) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val v1 = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

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