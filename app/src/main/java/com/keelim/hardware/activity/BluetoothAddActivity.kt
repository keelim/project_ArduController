package com.keelim.hardware.activity

import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_blueadd.*

class BluetoothAddActivity : AppCompatActivity() {
    private var mBluetoothAdapter: BluetoothAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blueadd)
        blueadd_tv1.text = "name $localBluetoothName"
        blueadd_tv2.text = "address $localBluetoothAddress"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.blueadd, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }


    private val localBluetoothName: String?
        get() {
            if (mBluetoothAdapter == null) {
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            }
            val name = mBluetoothAdapter!!.name
            if (name == null) {
                println("Name is null!")
            }
            return name
        }

    private val localBluetoothAddress: String?
        get() {
            if (mBluetoothAdapter == null) {
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            }
            val address = mBluetoothAdapter!!.address
            if (address == null) {
                println("Address is null!")
            }
            return address
        }

}