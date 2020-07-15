package com.keelim.hardware.view.fragments

import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_blueadd.*

class BluetoothAddFragments : Fragment(R.layout.activity_blueadd) {
    private lateinit var mBluetoothAdapter: BluetoothAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        blueadd_tv1.text = "name ${nameOrAddress("name")}"
        blueadd_tv2.text = "address ${nameOrAddress("address")}"
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    private fun nameOrAddress(parm: String): String? {

        if (parm == "name") {
            return mBluetoothAdapter.name
        } else if (parm == "address") {
            return mBluetoothAdapter.address
        } else return null
    }

}