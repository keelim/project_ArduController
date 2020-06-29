package com.keelim.hardware.activity

import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class TeleActivity : AppCompatActivity(R.layout.activity_tele) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        when (telephonyManager.simState) {
            TelephonyManager.SIM_STATE_ABSENT -> Toast.makeText(this, "Sim State Absent", Toast.LENGTH_SHORT).show()
            TelephonyManager.SIM_STATE_NETWORK_LOCKED -> Toast.makeText(this, "Sim State Network Locked", Toast.LENGTH_SHORT).show() // do something
            TelephonyManager.SIM_STATE_PIN_REQUIRED -> Toast.makeText(this, "Sim State Pin Required", Toast.LENGTH_SHORT).show() // do something
            TelephonyManager.SIM_STATE_PUK_REQUIRED -> Toast.makeText(this, "Sim State Puk Required", Toast.LENGTH_SHORT).show() // do something
            TelephonyManager.SIM_STATE_READY -> Toast.makeText(this, "Sim State Ready", Toast.LENGTH_SHORT).show() // do something
            TelephonyManager.SIM_STATE_UNKNOWN -> Toast.makeText(this, "Sim State Unknown", Toast.LENGTH_SHORT).show() // do something
        }
    }
}