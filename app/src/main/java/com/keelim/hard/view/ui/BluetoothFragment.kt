package com.keelim.hard.view.ui

import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.keelim.hard.R
import kotlinx.android.synthetic.main.fragment_blue.view.*


class BluetoothFragment : Fragment() {
    private lateinit var mBluetoothAdapter: BluetoothAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_blue, container, false)
        view.blue_tv1.text = "name ${nameOrAddress("name")}"
        view.blue_tv2.text = "address ${nameOrAddress("address")}"
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
//                mBluetoothAdapter.addressha
                "hard"
            }
            else -> null
        }
    }

}