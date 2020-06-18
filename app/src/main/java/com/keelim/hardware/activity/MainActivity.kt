package com.keelim.hardware.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.felipecsl.asymmetricgridview.library.Utils
import com.keelim.arducon.utils.BackPressCloseHandler
import com.keelim.hardware.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    //AutoCompleteTextView act;

    //Spinner spn;
    var items = arrayOf("Vibration Test", "Check Version Info", "SIM Card", "Proximity Sensor",
            "Flash Light", "Touch Sensor", "Display", "Light Sensor", "Pressure Sensor"
            , "Phone Buttons", "Speaker Test", "Wi-Fi Address", "Bluetooth Address", "Gravity sensor", "Magnetic Sensor", "Headphone",
            "Gyroscope", "GPS Location", "Battery Indicator", "Accelarometer")

    lateinit var backPressCloseHandler: BackPressCloseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        backPressCloseHandler = BackPressCloseHandler(this)

        val adapt = ArrayAdapter(this, R.layout.extraa, items) //리싸이클러로 변경
        listView1.adapter = adapt


        listView1.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> {
                    //not working
                    Intent(this, VibrationActivity::class.java).apply {
                        startActivity(this)
                    }

                }
                2 -> {
                    Intent(this, TeleActivity::class.java).apply {
                        startActivity(this)
                    }

                }
                1 -> {
                    //not whole info
                    Intent(applicationContext, SystemInfoActivity::class.java).apply {
                        startActivity(this)
                    }

                }
                3 -> {
                    Intent(applicationContext, ProximitySensorActivity::class.java)
                            .apply { startActivity(this) }
                }
                4 -> {
                    //not working
                    Intent(applicationContext, FlashActivity::class.java)
                            .apply { startActivity(this) }
                }
                5 -> {
                    Intent(applicationContext, TouchSensorActivity::class.java)
                            .apply { startActivity(this) }
                }
                6 -> {
                    //display multiple lighting
                    Intent(applicationContext, DisplayActivity::class.java)
                            .apply { startActivity(this) }
                }
                7 -> {
                    //light sensor display of dac values
                    Intent(applicationContext, LightSensorActivity::class.java)
                            .apply { startActivity(this) }
                }
                8 -> {
                    Intent(applicationContext, PressureActivity::class.java)
                            .apply { startActivity(this) }
                }
                9 -> {
                    //wrong the volume buttons,home,menu etc has to be checked here
                    Intent(applicationContext, ButtonTestingActivity::class.java)
                            .apply { startActivity(this) }
                }
                10 -> {
                    //wrong should have done that while taking calling position we here our voice
                    Intent(applicationContext, MicTestingActivity::class.java)
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
                    Intent(applicationContext, GravitySensorActivity::class.java)
                            .apply { startActivity(this) }
                }
                14 -> {
                    Intent(applicationContext, MagneticSensorActivity::class.java)
                            .apply { startActivity(this) }
                }
                15 -> {
                    //not working
                    Intent(applicationContext, HeadphoneActivity::class.java)
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

    override fun onBackPressed() {
        backPressCloseHandler.onBackPressed()
    }

}