package com.keelim.hard.view.ui

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.keelim.hard.R
import kotlinx.android.synthetic.main.fragment_pressure.*


class PressureFragment : Fragment(), SensorEventListener {

    private var sensmgr: SensorManager? = null
    private var accsensor: Sensor? = null
    private lateinit var sensorvalues: FloatArray

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_pressure,container, false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensmgr = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accsensor = sensmgr!!.getDefaultSensor(Sensor.TYPE_PRESSURE)
    }

    override fun onSensorChanged(event: SensorEvent) {
        sensorvalues = event.values
        val x = sensorvalues[0]
        press_tv!!.text = "x$x hPa"
    }

    override fun onResume() {
        sensmgr!!.registerListener(this, accsensor, SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onPause() {
        sensmgr!!.unregisterListener(this)
        super.onPause()
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }
}