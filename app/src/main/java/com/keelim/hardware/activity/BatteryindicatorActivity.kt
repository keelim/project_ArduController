package com.keelim.hardware.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class BatteryindicatorActivity : AppCompatActivity() {
    //Create Broadcast Receiver Object along with class definition
    private val mBatInfoReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        //When Event is published, onReceive method is called
        override fun onReceive(c: Context, i: Intent) {
            //Get Battery %
            val level = i.getIntExtra("level", 0)
            //Find the progressbar creating in main.xml
            val pb = findViewById<ProgressBar>(R.id.progressbar)
            //Set progress level with battery % value
            pb.progress = level
            //Find textview control created in main.xml
            val tv = findViewById<TextView>(R.id.textfield)
            //Set TextView with text
            tv.text = "Battery Level: $level%"
        }
    }

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Set layout we created
        setContentView(R.layout.activity_batteryindicator)
        registerReceiver(mBatInfoReceiver, IntentFilter(
                Intent.ACTION_BATTERY_CHANGED))
    }
}