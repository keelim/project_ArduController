package com.keelim.hardware.activity

import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_wifiaddress.*

class WifiAddressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifiaddress)
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val wInfo = wifiManager.connectionInfo
        var macAddress = wInfo.macAddress
        val n = WifiInfo.LINK_SPEED_UNITS
        val y = wInfo.ssid
        if (macAddress == null) {
            macAddress = "Device don't have mac address or wi-fi is disabled"
        }
        wifi_tv1!!.text = "mac address $macAddress"
        wifi_tv2!!.text = "ssid $y"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.wifiaddress, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}