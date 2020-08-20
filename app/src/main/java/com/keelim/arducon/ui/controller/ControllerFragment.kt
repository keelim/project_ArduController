package com.keelim.arducon.ui.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.keelim.arducon.R
import kotlinx.android.synthetic.main.fragment_controller.view.*

class ControllerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_controller, container, false)

        view.center_bt.setOnClickListener {
            Toast.makeText(requireActivity(), "center 버튼을 클릭하였습니다.", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}