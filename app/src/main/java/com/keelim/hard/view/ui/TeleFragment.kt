package com.keelim.hardware.view

import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.keelim.hard.R


class TeleFragment : Fragment() {
    private lateinit var telephonyManager: TelephonyManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        telephonyManager = requireActivity().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        val view = inflater.inflate(R.layout.fragment_tele, container, false)

        when (telephonyManager.simState) {
            TelephonyManager.SIM_STATE_ABSENT -> Toast.makeText(requireContext(), "Sim State Absent", Toast.LENGTH_SHORT).show()
            TelephonyManager.SIM_STATE_NETWORK_LOCKED -> Toast.makeText(requireContext(), "Sim State Network Locked", Toast.LENGTH_SHORT).show() // do something
            TelephonyManager.SIM_STATE_PIN_REQUIRED -> Toast.makeText(requireContext(), "Sim State Pin Required", Toast.LENGTH_SHORT).show() // do something
            TelephonyManager.SIM_STATE_PUK_REQUIRED -> Toast.makeText(requireContext(), "Sim State Puk Required", Toast.LENGTH_SHORT).show() // do something
            TelephonyManager.SIM_STATE_READY -> Toast.makeText(requireContext(), "Sim State Ready", Toast.LENGTH_SHORT).show() // do something
            TelephonyManager.SIM_STATE_UNKNOWN -> Toast.makeText(requireContext(), "Sim State Unknown", Toast.LENGTH_SHORT).show() // do something
        }

        return view
    }
}