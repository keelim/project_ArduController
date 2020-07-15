package com.keelim.hardware.view.fragments

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.keelim.hardware.view.customs.SingleTouchEventView
import java.util.zip.Inflater

class TouchSensorFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(SingleTouchEventView(requireActivity(), null))


        Toast.makeText(this, "화면에 원하는 그림을 그려주세요", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(S)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}