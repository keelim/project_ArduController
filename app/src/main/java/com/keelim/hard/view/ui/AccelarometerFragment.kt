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
import androidx.fragment.app.Fragment
import com.keelim.hard.R
import kotlinx.android.synthetic.main.fragment_accelarometer.*


class AccelarometerFragment : Fragment(), SensorEventListener {
    private lateinit var sensorValue: FloatArray
    private lateinit var sensmgr: SensorManager
    private lateinit var accsensor: Sensor


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_accelarometer, container, false)
        sensmgr = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accsensor = sensmgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        return view
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        sensorValue = event!!.values
        val x = sensorValue[0]
        val y = sensorValue[1]
        val z = sensorValue[2]
        press_tv.text = "x $x m/s^2"
        acc_tvy.text = "y $y m/s^2"
        acc_tvz.text = "z $z m/s^2"

        if (x > 15 || y > 15 || z > 15) {
            Toast.makeText(context, "phone shaked", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        sensmgr.registerListener(this, accsensor, SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onPause() {
        sensmgr.unregisterListener(this)
        super.onPause()
    }

}

