package com.keelim.hardware.activity

import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class WifiAddressActivity : AppCompatActivity() {
    private var t: TextView? = null
    private var v: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifiaddress)
        t = findViewById(R.id.textView32)
        v = findViewById(R.id.textView30)
        val wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wInfo = wifiManager.connectionInfo
        var macAddress = wInfo.macAddress
        val n = WifiInfo.LINK_SPEED_UNITS
        val y = wInfo.ssid
        if (macAddress == null) {
            macAddress = "Device don't have mac address or wi-fi is disabled"
        }
        t.setText("mac address $macAddress")
        v.setText("ssid $y")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.wifiaddress, menu)
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