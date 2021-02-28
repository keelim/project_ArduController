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
package com.keelim.hard.utils

import android.hardware.Sensor
import com.keelim.hard.model.DeviceInformation
import org.json.JSONArray
import org.json.JSONObject

class ManageJson {
  companion object {
    fun makeList(sensors: List<Sensor>): String {
      val jsonArray = JSONArray()

      for (element in sensors) {
        val dto = DeviceInformation(element)
        val jsonObject: JSONObject = JSONObject().apply {
          put("name", dto.name)
          put("power", dto.power)
          put("resolution", dto.resolution)
          put("range", dto.range)
          put("vendor", dto.vendor)
          put("mindelay", dto.mindelay)
        }
        jsonArray.put(jsonObject)
      }
      return jsonArray.toString()
    }
  }
}
