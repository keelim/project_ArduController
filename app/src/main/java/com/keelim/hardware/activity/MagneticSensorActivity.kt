package com.keelim.hardware.activity

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_magneticsensor.*

class MagneticSensorActivity : AppCompatActivity(R.layout.activity_magneticsensor), SensorEventListener {
    private lateinit var sensmgr: SensorManager
    private lateinit var magsensor: Sensor
    private lateinit var sensorvalues: FloatArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensmgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        magsensor = sensmgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }


    override fun onSensorChanged(event: SensorEvent) {
        sensorvalues = event.values
        val x = sensorvalues[0]
        val y = sensorvalues[1]
        val z = sensorvalues[2]
        mag_tv1!!.text = "x $x uT"
        mag_tv2!!.text = "y $y uT"
        mag_tv3!!.text = "z $z uT"
    }

    override fun onResume() {
        sensmgr.registerListener(this, magsensor, SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onPause() {
        sensmgr.unregisterListener(this)
        super.onPause()
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }
}