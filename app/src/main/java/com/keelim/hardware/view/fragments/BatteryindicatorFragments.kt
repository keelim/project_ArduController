package com.keelim.hardware.view.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_batteryindicator.*

class BatteryindicatorFragments : Fragment(R.layout.activity_batteryindicator) {

    private val mBatInfoReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(c: Context, i: Intent) {
            val level = i.getIntExtra("level", 0)
            progressbar.progress = level
            textfield.text = "Battery Level: $level%"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().registerReceiver(
            mBatInfoReceiver,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}