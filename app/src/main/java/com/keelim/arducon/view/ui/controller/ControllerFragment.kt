package com.keelim.arducon.view.ui.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.convertTo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.keelim.arducon.R
import com.keelim.arducon.view.R

class ControllerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_controller, container, false)

        view.center_bt.setOnClickListener {
            Toast.makeText(this, "center 버튼을 클릭하였습니다.", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}