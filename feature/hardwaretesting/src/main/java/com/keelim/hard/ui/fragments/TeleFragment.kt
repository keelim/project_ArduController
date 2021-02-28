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
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.keelim.hard.R
import kotlinx.android.synthetic.main.fragment_tele.view.*

class TeleFragment : Fragment() {
  private lateinit var telephonyManager: TelephonyManager
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    telephonyManager = requireActivity().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    val view = inflater.inflate(R.layout.fragment_tele, container, false)
    (requireActivity() as AppCompatActivity).supportActionBar!!.hide()

    when (telephonyManager.simState) {
      TelephonyManager.SIM_STATE_NETWORK_LOCKED -> view.tele_tv.text = "Sim State network Locked"
      TelephonyManager.SIM_STATE_PIN_REQUIRED -> view.tele_tv.text = "Sim State Pin Required"
      TelephonyManager.SIM_STATE_PUK_REQUIRED -> view.tele_tv.text = "Sim State Puk Required"
      TelephonyManager.SIM_STATE_UNKNOWN -> view.tele_tv.text = "Sim State Unknown"
      TelephonyManager.SIM_STATE_ABSENT -> view.tele_tv.text = "Sim State Absent"
      TelephonyManager.SIM_STATE_READY -> view.tele_tv.text = "Sim State Ready"
    }

    return view
  }
}
