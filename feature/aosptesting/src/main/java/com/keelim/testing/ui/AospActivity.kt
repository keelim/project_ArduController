package com.keelim.testing.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.testing.databinding.ActivityAospBinding
import com.keelim.testing.ui.handlertest.HandlerTestActivity
import com.keelim.testing.ui.windowtest.AddWindowTestActivity

class AospActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAospBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAospBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTest1.setOnClickListener {
            Intent(this, AddWindowTestActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        binding.btnTest2.setOnClickListener {
            Intent(this, HandlerTestActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }
    }
}