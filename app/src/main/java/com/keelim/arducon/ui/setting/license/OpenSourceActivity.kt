package com.keelim.arducon.ui.setting.license

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.arducon.databinding.ActivityOpenSourceBinding

class
OpenSourceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOpenSourceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenSourceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}