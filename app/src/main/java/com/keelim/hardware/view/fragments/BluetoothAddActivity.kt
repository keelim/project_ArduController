package com.keelim.hardware.view

import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_blueadd.*

class BluetoothAddActivity : AppCompatActivity(R.layout.activity_blueadd) {
    private var mBluetoothAdapter: BluetoothAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        blueadd_tv1.text = "name ${nameOrAddress("name")}"
        blueadd_tv2.text = "address ${nameOrAddress("address")}"
    }

    private fun nameOrAddress(parm: String): String? {
        if (mBluetoothAdapter == null) {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        }

        if (parm == "name") {
            return mBluetoothAdapter!!.name
        } else if (parm == "address") {
            return mBluetoothAdapter!!.address
        } else return null
    }

}