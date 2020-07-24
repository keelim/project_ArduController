package com.keelim.hardware.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_gpsloc.*

class GpslocActivity : AppCompatActivity(R.layout.activity_gpsloc) { // 무슨 관계인지 확인 해보기
    private var latitude = 0.0
    private var longitude = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
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