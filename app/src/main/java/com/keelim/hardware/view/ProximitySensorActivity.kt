package com.keelim.hardware.view

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_proximity_sensor.*

class ProximitySensorActivity : AppCompatActivity(R.layout.activity_proximity_sensor), SensorEventListener {
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //create instance of sensor manager and get system service to interact with Sensor
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        if (proximitySensor == null) {
            Toast.makeText(this, "No Proximity Sensor Found! ", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        // register this class as a listener for the Proximity Sensor
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        // unregister listener
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}


    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
            if (event.values[0].equals(0.0f)) {
                prox_tv1!!.text = "You are Near: ${event.values[0]}"
            } else {
                prox_tv1!!.text = "You are Far: ${event.values[0]}"
            }
        }
    }
}