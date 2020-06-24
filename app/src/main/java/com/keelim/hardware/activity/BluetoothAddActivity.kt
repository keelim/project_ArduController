package com.keelim.hardware.activity

import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class BluetoothAddActivity : AppCompatActivity() {
    private var mBluetoothAdapter: BluetoothAdapter? = null
    lateinit var r: TextView
    private lateinit var t: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blueadd)
        r = findViewById(R.id.textView12)
        t = findViewById(R.id.textView41)
        r.text = "name $localBluetoothName"
        t.text = "address $localBluetoothAddress"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.blueadd, menu)
        return true
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