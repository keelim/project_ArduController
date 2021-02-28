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
import android.hardware.SensorManager
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.keelim.hard.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
  private lateinit var list: MutableList<Sensor>
  private lateinit var sm: SensorManager

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    val view = inflater.inflate(R.layout.fragment_home, container, false)

    sm = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    list = sm.getSensorList(Sensor.TYPE_ALL)

    val i = 0
    val str = StringBuilder()
      .append("전체 센서 수: ").append(list.size).append("\n")

    for (s in list) {
      str.append("").append(i).append(" name: ").append(s.name).append("\n")
        .append("power: ").append(s.power).append("\n").append("resolution: ").append(s.resolution).append("\n")
        .append("range: ").append(s.maximumRange).append("\n").append("vendor: ").append(s.vendor).append("\n")
        .append("min delay: ").append(s.minDelay).append("\n\n")
    }

    view.after_text.movementMethod = ScrollingMovementMethod()
    view.after_text.text = str.toString()

    return view
  }

  override fun onResume() {
    super.onResume()
    (requireActivity() as AppCompatActivity).supportActionBar!!.show()
  }
}
