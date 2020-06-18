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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R

class AccelarometerActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var tvx: TextView
    private lateinit var tvy: TextView
    private lateinit var tvz: TextView
    private lateinit var sensmgr: SensorManager
    private lateinit var accsensor: Sensor
    private lateinit var sensorValue: FloatArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accelarometer)
        tvx = findViewById(R.id.textView00)
        tvy = findViewById(R.id.textView2)
        tvz = findViewById(R.id.textView3)
        sensmgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accsensor = sensmgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.accelarometer, menu)
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
        sensorValue = event.values
        val x = sensorValue[0]
        val y = sensorValue[1]
        val z = sensorValue[2]
        tvx.text = "x $x m/s^2"
        tvy.text = "y $y m/s^2"
        tvz.text = "z $z m/s^2"
        if (x > 15 || y > 15 || z > 15) {
            Toast.makeText(this, "phone shaked", Toast.LENGTH_LONG).show()
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

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}