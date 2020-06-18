package com.keelim.hardware.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.keelim.hardware.R

class HeadphoneActivity : AppCompatActivity() {
    private var myReceiver: MusicIntentReceiver? = null
    var s: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headphone)
        s = findViewById(R.id.textView14)
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
                    0 -> s!!.text = "Headset is unplugged"
                    1 -> s!!.text = "Headset is plugged"
                    else -> s!!.text = "Plugin the headset"
                }
            }
        }
    }

    override fun onPause() {
        unregisterReceiver(myReceiver)
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.headphone, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}