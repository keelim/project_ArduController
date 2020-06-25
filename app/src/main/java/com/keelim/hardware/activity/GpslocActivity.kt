package com.keelim.hardware.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_gpsloc.*

class GpslocActivity : AppCompatActivity() {
    private var latitude = 0.0
    private var longitude = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gpsloc)
        val gps = GpslocateActivity(this)
        latitude = gps.getLatitude()
        longitude = gps.getLongitude()
    }

    override fun onStart() {
        super.onStart()
        gps_tv1!!.text = "latitude $latitude"
        gps_tv2!!.text = "longitude $longitude"
    }
}