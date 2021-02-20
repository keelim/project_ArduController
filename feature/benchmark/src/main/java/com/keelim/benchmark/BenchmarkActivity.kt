package com.keelim.benchmark

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.benchmark.databinding.ActivityBenchmarkBinding

class BenchmarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBenchmarkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBenchmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnList.setOnClickListener {
            val intent = Intent(this, ListViewActivity::class.java)
            startActivity(intent)
        }

        binding.btnHttp.setOnClickListener {
            val intent = Intent(this, HttpActivity::class.java)
            startActivity(intent)
        }

        binding.btnStorage.setOnClickListener {
            val intent = Intent(this, StorageActivity::class.java)
            startActivity(intent)
        }

        binding.btnViewStack.setOnClickListener {
            val intent = Intent(this, ViewStackActivity::class.java)
            startActivity(intent)
        }

        binding.btnChart.setOnClickListener {
            val intent = Intent(this, ChartActivity::class.java)
            startActivity(intent)
        }

    }
}
