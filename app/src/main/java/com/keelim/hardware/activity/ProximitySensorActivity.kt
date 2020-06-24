package com.keelim.hardware.activity

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class ProximitySensorActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var tVProximity: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximity_sensor)
        tVProximity = findViewById(R.id.tVProximity)
        //create instance of sensor manager and get system service to interact with Sensor
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val proximitySensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        if (proximitySensor == null) {
            Toast.makeText(this@ProximitySensorActivity, "No Proximity Sensor Found! ", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        // register this class as a listener for the Proximity Sensor
        sensorManager!!.registerListener(this,
                sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        // unregister listener
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    // called when sensor value have changed
    override fun onSensorChanged(event: SensorEvent) {
        // The Proximity sensor returns a single value either 0 or 5(also 1 depends on Sensor manufacturer).
        // 0 for near and 5 for far 
        if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
            if (event.values[0].equals(0.0f)) {
                tVProximity!!.text = "You are Near: " + event.values[0]
            } else {
                tVProximity!!.text = "You are Far: " + event.values[0]
            }
        }
    }
}