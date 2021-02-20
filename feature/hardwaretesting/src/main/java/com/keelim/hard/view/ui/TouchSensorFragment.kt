package com.keelim.hard.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.keelim.hard.view.custom.SingleTouchEventView

class TouchSensorFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (requireActivity() as AppCompatActivity).supportActionBar!!.hide()
        Toast.makeText(requireContext(), "원하는 그림을 그려주세요", Toast.LENGTH_SHORT).show()
        return SingleTouchEventView(container!!.context, null)
    }
}