/*
 * Designed and developed by 2020 keelim (Jaehyun Kim)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.keelim.hard.ui.fragments

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
import kotlinx.android.synthetic.main.fragment_accelarometer.*

class AccelarometerFragment : Fragment(), SensorEventListener {
  private lateinit var sensorValue: FloatArray
  private lateinit var sensmgr: SensorManager
  private lateinit var accsensor: Sensor

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_accelarometer, container, false)
    (requireActivity() as AppCompatActivity).supportActionBar!!.hide()
    sensmgr = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    accsensor = sensmgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    return view
  }

  override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

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
