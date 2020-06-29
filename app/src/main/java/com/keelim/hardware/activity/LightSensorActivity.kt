package com.keelim.hardware.activity

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_lightsensor.*

class LightSensorActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensmgr: SensorManager
    private lateinit var accsensor: Sensor
    private lateinit var sensorvalues: FloatArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lightsensor)
        sensmgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accsensor = sensmgr.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onSensorChanged(event: SensorEvent) {
        sensorvalues = event.values
        val x = sensorvalues[0]
        light_tv1!!.text = "DAC $x luxes"
    }

    override fun onResume() {
        sensmgr.registerListener(this, accsensor, SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onPause() {
        sensmgr.unregisterListener(this)
        super.onPause()
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }
}