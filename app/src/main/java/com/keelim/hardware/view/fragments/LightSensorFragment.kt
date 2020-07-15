package com.keelim.hardware.view.fragments

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_lightsensor.*

class LightSensorFragment : Fragment(R.layout.activity_lightsensor), SensorEventListener {
    private lateinit var sensmgr: SensorManager
    private lateinit var accsensor: Sensor
    private lateinit var sensorvalues: FloatArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensmgr = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
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