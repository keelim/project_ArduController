package com.keelim.hardware.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.keelim.hardware.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    //AutoCompleteTextView act;

    //Spinner spn;
    var items = arrayOf("Vibration Test", "Check Version Info", "SIM Card", "Proximity Sensor",
            "Flash Light", "Touch Sensor", "Display", "Light Sensor", "Pressure Sensor"
            , "Phone Buttons", "Speaker Test", "Wi-Fi Address", "Bluetooth Address", "Gravity sensor", "Magnetic Sensor", "Headphone",
            "Gyroscope", "GPS Location", "Battery Indicator", "Accelarometer")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapt = ArrayAdapter(this, R.layout.extraa, items) //리싸이클러로 변경
        listView1.adapter = adapt

        listView1.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> {
                    //not working
                    Intent(this, Vibration::class.java).apply {
                        startActivity(this)
                    }

                }
                2 -> {
                    Intent(this, Tele::class.java).apply {
                        startActivity(this)
                    }

                }
                1 -> {
                    //not whole info
                    Intent(applicationContext, Systeminfo::class.java).apply {
                        startActivity(this)
                    }

                }
                3 -> {
                    Intent(applicationContext, ProximitySensor::class.java)
                            .apply { startActivity(this) }
                }
                4 -> {
                    //not working
                    Intent(applicationContext, Flash::class.java)
                            .apply { startActivity(this) }
                }
                5 -> {
                    Intent(applicationContext, TouchSensor::class.java)
                            .apply { startActivity(this) }
                }
                6 -> {
                    //display multiple lighting
                    Intent(applicationContext, Display::class.java)
                            .apply { startActivity(this) }
                }
                7 -> {
                    //light sensor display of dac values
                    Intent(applicationContext, Lightsensor::class.java)
                            .apply { startActivity(this) }
                }
                8 -> {
                    Intent(applicationContext, Pressure::class.java)
                            .apply { startActivity(this) }
                }
                9 -> {
                    //wrong the volume buttons,home,menu etc has to be checked here
                    Intent(applicationContext, Buttontesting::class.java)
                            .apply { startActivity(this) }
                }
                10 -> {
                    //wrong should have done that while taking calling position we here our voice
                    Intent(applicationContext, Mictesting::class.java)
                            .apply { startActivity(this) }
                }
                11 -> {
                    Intent(applicationContext, WifiAddressActivity::class.java)
                            .apply { startActivity(this) }
                }
                12 -> {
                    Intent(applicationContext, BluetoothAddActivity::class.java)
                            .apply { startActivity(this) }
                }
                13 -> {
                    Intent(applicationContext, Gravitysensor::class.java)
                            .apply { startActivity(this) }
                }
                14 -> {
                    Intent(applicationContext, MagneticSensorActivity::class.java)
                            .apply { startActivity(this) }
                }
                15 -> {
                    //not working
                    Intent(applicationContext, Headphone::class.java)
                            .apply { startActivity(this) }
                }
                16 -> {
                    Intent(applicationContext, GyroscopeActivity::class.java)
                            .apply { startActivity(this) }
                }
                17 -> {
                    //test again
                    Intent(applicationContext, GpslocActivity::class.java)
                            .apply { startActivity(this) }
                }
                18 -> {
                    Intent(applicationContext, BatteryindicatorActivity::class.java)
                            .apply { startActivity(this) }
                }
                19 -> {
                    Intent(applicationContext, AccelarometerActivity::class.java)
                            .apply { startActivity(this) }
                }

            }
        }
    }

}