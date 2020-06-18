package com.keelim.hardware.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class GpslocActivity : AppCompatActivity() {
    private var tr: TextView? = null
    private var ts: TextView? = null
    private var latitude = 0.0
    private var longitude = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gpsloc)
        val gps = GpslocateActivity(this)
        latitude = gps.getLatitude()
        longitude = gps.getLongitude()
        tr = findViewById(R.id.textView60)
        ts = findViewById(R.id.textView61)
    }

    override fun onStart() {
        tr!!.text = "latitude $latitude"
        ts!!.text = "longitude $longitude"
        super.onStart()
    }
}