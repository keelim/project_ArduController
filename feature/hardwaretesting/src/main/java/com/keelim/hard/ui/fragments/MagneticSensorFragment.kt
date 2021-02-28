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
import kotlinx.android.synthetic.main.fragment_magnetic.*

class MagneticSensorFragment : Fragment(), SensorEventListener {
  private lateinit var sensmgr: SensorManager
  private lateinit var magsensor: Sensor
  private lateinit var sensorvalues: FloatArray

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_magnetic, container, false)
    (requireActivity() as AppCompatActivity).supportActionBar!!.hide()
    sensmgr = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    magsensor = sensmgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    return view
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
