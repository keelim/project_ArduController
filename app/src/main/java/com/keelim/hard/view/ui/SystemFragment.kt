package com.keelim.hardware.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.keelim.hard.R
import com.keelim.hard.model.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_system.view.*


class SystemFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_system, container, false)

        view.recycler_text.adapter = RecyclerAdapter(makeList())
        view.recycler_text.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    private fun makeList(): MutableList<String> {
        return mutableListOf(Build.VERSION.RELEASE,
                Build.VERSION.SDK_INT.toString(),
                Build.VERSION.CODENAME,
                Build.VERSION.INCREMENTAL,
                Build.BOARD,
                Build.BOOTLOADER,
                Build.BRAND,
                Build.DEVICE,
                Build.HARDWARE,
                Build.MANUFACTURER)
    }

}