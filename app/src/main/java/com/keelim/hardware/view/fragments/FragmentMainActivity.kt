package com.keelim.hardware.view.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.keelim.hardware.R
import com.keelim.hardware.model.TextAdapter
import com.keelim.hardware.utils.BackPressCloseHandler
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class FragmentMainActivity : AppCompatActivity(R.layout.activity_fragment_main) {

    private var items = arrayOf("Vibration Test", "Check Version Info", "SIM Card", "Proximity Sensor",
            "Flash Light", "Touch Sensor", "Display", "Light Sensor", "Pressure Sensor"
            , "Phone Buttons", "Speaker Test", "Wi-Fi Address", "Bluetooth Address", "Gravity sensor", "Magnetic Sensor", "Headphone",
            "Gyroscope", "GPS Location", "Battery Indicator", "Accelarometer", "LAB Activity")

    private lateinit var backPressCloseHandler: BackPressCloseHandler

    //text 뷰 하나 그리고

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backPressCloseHandler = BackPressCloseHandler(this)

        recycler1.layoutManager = LinearLayoutManager(this)
        val arrayList = ArrayList<String>()
        for(a in items ){
            arrayList.add(a)
        }

        val adapter = TextAdapter(this, arrayList)
        recycler1.adapter = adapter
    }


    override fun onBackPressed() {
        backPressCloseHandler.onBackPressed()
    }
}