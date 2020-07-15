package com.keelim.hardware.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.keelim.aio.Open
import com.keelim.hardware.R

class TestFragment : Fragment() {
    lateinit var open :Open

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        open = Open.OpenSystemBuilder(this)
            .setSYSTEM_BOARD(true)
            .setSYSTEM_BOOTLOADER(true)
            .build()


    }
}