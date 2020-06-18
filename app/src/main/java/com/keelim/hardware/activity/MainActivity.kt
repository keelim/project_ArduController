package com.keelim.hardware.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import com.keelim.hardware.utils.BackPressCloseHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //AutoCompleteTextView act;

    //Spinner spn;
    var items = arrayOf("Vibration Test", "Check Version Info", "SIM Card", "Proximity Sensor",
            "Flash Light", "Touch Sensor", "Display", "Light Sensor", "Pressure Sensor"
            , "Phone Buttons", "Speaker Test", "Wi-Fi Address", "Bluetooth Address", "Gravity sensor", "Magnetic Sensor", "Headphone",
            "Gyroscope", "GPS Location", "Battery Indicator", "Accelarometer")

    private lateinit var backPressCloseHandler: BackPressCloseHandler

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
                    Intent(this, SystemInfoActivity::class.java).apply {
                        startActivity(this)
                    }

                }
                3 -> {
                    Intent(this, ProximitySensorActivity::class.java)
                            .apply { startActivity(this) }
                }
                4 -> {
                    //not working
//                    Intent(this, FlashActivity::class.java)
//                            .apply { startActivity(this) }
                }
                5 -> {
                    Intent(this, TouchSensorActivity::class.java)
                            .apply { startActivity(this) }
                }
                6 -> {
                    //display multiple lighting
                    Intent(this, DisplayActivity::class.java)
                            .apply { startActivity(this) }
                }
                7 -> {
                    //light sensor display of dac values
                    Intent(this, LightSensorActivity::class.java)
                            .apply { startActivity(this) }
                }
                8 -> {
                    Intent(this, PressureActivity::class.java)
                            .apply { startActivity(this) }
                }
                9 -> {
                    //wrong the volume buttons,home,menu etc has to be checked here
                    Intent(this, ButtonTestingActivity::class.java)
                            .apply { startActivity(this) }
                }
                10 -> {
                    //wrong should have done that while taking calling position we here our voice
                    Intent(this, MicTestingActivity::class.java)
                            .apply { startActivity(this) }
                }
                11 -> {
                    Intent(this, WifiAddressActivity::class.java)
                            .apply { startActivity(this) }
                }
                12 -> {
                    Intent(this, BluetoothAddActivity::class.java)
                            .apply { startActivity(this) }
                }
                13 -> {
                    Intent(this, GravitySensorActivity::class.java)
                            .apply { startActivity(this) }
                }
                14 -> {
                    Intent(this, MagneticSensorActivity::class.java)
                            .apply { startActivity(this) }
                }
                15 -> {
                    //not working
                    Intent(this, HeadphoneActivity::class.java)
                            .apply { startActivity(this) }
                }
                16 -> {
                    Intent(this, GyroscopeActivity::class.java)
                            .apply { startActivity(this) }
                }
                17 -> {
                    //test again
                    Intent(this, GpslocActivity::class.java)
                            .apply { startActivity(this) }
                }
                18 -> {
                    Intent(this, BatteryindicatorActivity::class.java)
                            .apply { startActivity(this) }
                }
                19 -> {
                    Intent(this, AccelarometerActivity::class.java)
                            .apply { startActivity(this) }
                }

            }
        }
    }

    override fun onBackPressed() {
        backPressCloseHandler.onBackPressed()
    }

}