package com.keelim.hardware.view.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_wifiaddress.*

class WifiFragment : Fragment(R.layout.activity_wifiaddress) {

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val wifiManager = requireActivity().getSystemService(Context.WIFI_SERVICE) as WifiManager

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

}