package com.keelim.hardware.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_gpsloc.*

class GpslocFragment : Fragment(R.layout.activity_gpsloc) { // 무슨 관계인지 확인 해보기
    private var latitude = 0.0
    private var longitude = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val gps = GpslocateFragment(requireContext())
        latitude = gps.getLatitude()
        longitude = gps.getLongitude()
    }

    override fun onStart() {
        super.onStart()
        gps_tv1!!.text = "latitude $latitude"
        gps_tv2!!.text = "longitude $longitude"
    }
}