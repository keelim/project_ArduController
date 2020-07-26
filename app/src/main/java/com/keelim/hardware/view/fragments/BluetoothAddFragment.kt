package com.keelim.hardware.view.fragments

import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_blueadd.view.*

class BluetoothAddFragment : Fragment() {
    private lateinit var mBluetoothAdapter: BluetoothAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.activity_blueadd, container, false)
        view.blueadd_tv1.text = "name ${nameOrAddress("name")}"
        view.blueadd_tv2.text = "address ${nameOrAddress("address")}"
        return view
    }

    private fun nameOrAddress(s: String): String? {
        if (mBluetoothAdapter == null) {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        }

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