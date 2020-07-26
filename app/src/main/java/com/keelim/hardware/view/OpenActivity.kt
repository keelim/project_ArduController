package com.keelim.hardware.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.aio.Open
import com.keelim.hardware.R

class OpenActivity : AppCompatActivity() {
    private lateinit var open :Open

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open)
        open = Open.OpenSystemBuilder(this)
            .setSYSTEM_BOARD(true)
            .setSYSTEM_BOOTLOADER(true)
            .build()
    }
}