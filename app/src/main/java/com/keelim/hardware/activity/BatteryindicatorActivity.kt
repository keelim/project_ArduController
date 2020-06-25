package com.keelim.hardware.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_batteryindicator.*

class BatteryindicatorActivity : AppCompatActivity(R.layout.activity_batteryindicator) {
    //Create Broadcast Receiver Object along with class definition
    private val mBatInfoReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        //When Event is published, onReceive method is called
        override fun onReceive(c: Context, i: Intent) {
            //Get Battery %
            val level = i.getIntExtra("level", 0)
            //Find the progressbar creating in main.xml
            //Set progress level with battery % value
            progressbar.progress = level
            //Find textview control created in main.xml
            //Set TextView with text
            textfield.text = "Battery Level: $level%"
        }
    }

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(mBatInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }
}