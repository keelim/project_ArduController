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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.keelim.hard.R
import kotlinx.android.synthetic.main.fragment_proximity.*

class ProximitySensorFragment : Fragment(), SensorEventListener {
    private var sensorManager: SensorManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_proximity, container, false)

        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        (requireActivity() as AppCompatActivity).supportActionBar!!.hide()

        val proximitySensor = sensorManager?.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if (proximitySensor == null) {
            Toast.makeText(requireContext(), "No Proximity Sensor Found! ", Toast.LENGTH_LONG).show()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
            if (event.values[0].equals(0.0f)) {
                prox_tv1!!.text = "You are Near: ${event.values[0]}"
            } else {
                prox_tv2!!.text = "You are Far: ${event.values[0]}"
            }
        }
    }
}