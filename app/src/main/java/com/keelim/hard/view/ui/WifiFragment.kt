package com.keelim.hard.view.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.keelim.hard.R
import kotlinx.android.synthetic.main.fragment_wifi.*

class WifiFragment : Fragment() {

    @SuppressLint("HardwareIds")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wifi, container, false)

        val wifiManager = requireActivity().getSystemService(Context.WIFI_SERVICE) as WifiManager

        val wInfo = wifiManager.connectionInfo
        var macAddress = wInfo.macAddress
        if (macAddress == null) macAddress = "Device don't have mac address or wi-fi is disabled"

        val n = WifiInfo.LINK_SPEED_UNITS
        val y = wInfo.ssid

        wifi_tv1.text = "mac address: $macAddress"
        wifi_tv2.text = "ssid $y"
        wifi_tv3.text = "LINK_SPEED_UNITS $n"

        return view
    }

}
