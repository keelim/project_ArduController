package com.keelim.hard.utils


import android.hardware.Sensor
import com.keelim.hard.model.DTO

import org.json.JSONArray
import org.json.JSONObject


class ManageJson {
    companion object {
        fun makeList(sensors: List<Sensor>): String {
            val jsonArray = JSONArray()

            for(i in 0..sensors.size){
                val dto = DTO(sensors[i])
                val jsonObject : JSONObject = JSONObject() .apply {
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

