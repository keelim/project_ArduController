package com.keelim.hardware.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_headphone.*

class HeadphoneActivity : AppCompatActivity(R.layout.activity_headphone) {
    private lateinit var myReceiver: MusicIntentReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myReceiver = MusicIntentReceiver()
    }

    override fun onResume() {
        val filter = IntentFilter(Intent.ACTION_HEADSET_PLUG)
        registerReceiver(myReceiver, filter)
        super.onResume()
    }

    private inner class MusicIntentReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == Intent.ACTION_HEADSET_PLUG) {
                when (intent.getIntExtra("state", -1)) {
                    0 -> head_tv1!!.text = "Headset is unplugged"
                    1 -> head_tv1!!.text = "Headset is plugged"
                    else -> head_tv1!!.text = "Plugin the headset"
                }
            }
        }
    }

    override fun onPause() {
        unregisterReceiver(myReceiver)
        super.onPause()
    }

}