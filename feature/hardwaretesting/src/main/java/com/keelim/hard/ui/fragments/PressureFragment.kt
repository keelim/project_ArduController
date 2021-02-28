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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.keelim.hard.R
import kotlinx.android.synthetic.main.fragment_pressure.*

class PressureFragment : Fragment(), SensorEventListener {

  private var sensmgr: SensorManager? = null
  private var accsensor: Sensor? = null
  private lateinit var sensorvalues: FloatArray

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    (requireActivity() as AppCompatActivity).supportActionBar!!.hide()
    return inflater.inflate(R.layout.fragment_pressure, container, false)
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
