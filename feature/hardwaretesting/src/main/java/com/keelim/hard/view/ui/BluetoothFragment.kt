package com.keelim.hard.view.ui

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.keelim.hard.R
import kotlinx.android.synthetic.main.fragment_blue.view.*

class BluetoothFragment : Fragment() {
    private lateinit var mBluetoothAdapter: BluetoothAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_blue, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar!!.hide()
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        view.blue_tv1.text = "Bluetooth Device name ${nameOrAddress("name")}"
        view.blue_tv2.text = "Bluetooth address ${nameOrAddress("address")}"
        return view
    }

    @SuppressLint("HardwareIds")
    private fun nameOrAddress(s: String): String? {

        return when (s) {
            "name" -> {
                mBluetoothAdapter.name
            }
            "address" -> {
                mBluetoothAdapter.address
            }
            else -> null
        }
    }

}