package com.keelim.hardware.activity

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class GyroscopeActivity : AppCompatActivity(), SensorEventListener {
    private var tvx: TextView? = null
    private var tvy: TextView? = null
    private var tvz: TextView? = null
    private var sensmgr: SensorManager? = null
    private var gyrosensor: Sensor? = null
    private lateinit var sensorvalues: FloatArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gyroscope)
        tvx = findViewById(R.id.textView8)
        tvy = findViewById(R.id.textView9)
        tvz = findViewById(R.id.textView10)
        sensmgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyrosensor = sensmgr!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.gyroscope, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onSensorChanged(event: SensorEvent) {
        sensorvalues = event.values
        val x = sensorvalues[0]
        val y = sensorvalues[1]
        val z = sensorvalues[2]
        tvx!!.text = "x $x rad/sec"
        tvy!!.text = "y $y rad/sec"
        tvz!!.text = "z $z rad/sec"
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
        // TODO Auto-generated method stub
    }
}