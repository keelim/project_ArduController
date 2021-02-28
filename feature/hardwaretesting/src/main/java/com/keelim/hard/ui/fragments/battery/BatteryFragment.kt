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
package com.keelim.hard.ui.fragments.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.keelim.hard.R
import com.keelim.hard.databinding.FragmentBatteryBinding

class BatteryFragment : Fragment() {
  private lateinit var binding: FragmentBatteryBinding
  private val viewModel by viewModels<BatteryViewModel>() // delegation
  private val mBatInfoReceiver: BroadcastReceiver = object : BroadcastReceiver() {
    override fun onReceive(c: Context, i: Intent) {
      val level = i.getIntExtra("level", 0)
      viewModel.updateLevel(level)
      viewModel.update(level)
//      battery_progressbar.progress = level
//      battery_tv.text = "Battery Level: $level%"
    }
  }
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_battery, container, false)

    (requireActivity() as AppCompatActivity).supportActionBar!!.hide()
    requireActivity().registerReceiver(mBatInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.vm = viewModel
    binding.lifecycleOwner = viewLifecycleOwner
  }
}
