package com.keelim.hardware.activity

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_gravitysensor.*

class GravitySensorActivity : AppCompatActivity(R.layout.activity_gravitysensor), SensorEventListener {
    private var sensmgr: SensorManager? = null
    private var accsensor: Sensor? = null
    private lateinit var sensorvalues: FloatArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensmgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accsensor = sensmgr!!.getDefaultSensor(Sensor.TYPE_GRAVITY)
    }


    override fun onSensorChanged(event: SensorEvent) {
        sensorvalues = event.values
        val x = sensorvalues[0]
        val y = sensorvalues[1]
        val z = sensorvalues[2]
        gra_tv1!!.text = "x $x ms^2"
        gra_tv2!!.text = "y $y ms^2"
        gra_tv3!!.text = "z $z ms^2"
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