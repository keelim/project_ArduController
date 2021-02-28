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

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.keelim.hard.R
import kotlinx.android.synthetic.main.fragment_blue.view.*

class BluetoothFragment : Fragment() {
  private lateinit var mBluetoothAdapter: BluetoothAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_blue, container, false)
    (requireActivity() as AppCompatActivity).supportActionBar!!.hide()
    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    view.blue_tv1.text = "Bluetooth Device name ${nameOrAddress("name")}"
    view.blue_tv2.text = "Bluetooth address ${nameOrAddress("address")}"
    return view
  }

  @SuppressLint("HardwareIds")
  private fun nameOrAddress(s: String): String? {

    return when (s) {
      "name" -> {
        mBluetoothAdapter.name
      }
      "address" -> {
        mBluetoothAdapter.address
      }
      else -> null
    }
  }
}
