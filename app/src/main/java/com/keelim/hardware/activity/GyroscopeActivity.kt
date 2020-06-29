package com.keelim.hardware.activity

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_gyroscope.*

class GyroscopeActivity : AppCompatActivity(R.layout.activity_gyroscope), SensorEventListener {
    private var sensmgr: SensorManager? = null
    private var gyrosensor: Sensor? = null
    private lateinit var sensorvalues: FloatArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensmgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyrosensor = sensmgr!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }


    override fun onSensorChanged(event: SensorEvent) {
        sensorvalues = event.values
        val x = sensorvalues[0]
        val y = sensorvalues[1]
        val z = sensorvalues[2]
        gyr_tv1!!.text = "x $x rad/sec"
        gyr_tv2!!.text = "y $y rad/sec"
        gyr_tv3!!.text = "z $z rad/sec"
    }

    override fun onResume() {
        sensmgr!!.registerListener(this, gyrosensor, SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onPause() {
        sensmgr!!.unregisterListener(this)
        super.onPause()
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        
    }
}