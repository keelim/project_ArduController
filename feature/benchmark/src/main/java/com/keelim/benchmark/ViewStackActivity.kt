package com.keelim.benchmark


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.benchmark.databinding.ActivityViewStackBinding

class ViewStackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewStackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewStackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title ="View Stack Benchmark"
    }
}
