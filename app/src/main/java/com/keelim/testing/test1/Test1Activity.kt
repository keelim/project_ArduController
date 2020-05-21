package com.keelim.testing.test1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.testing.R

class Test1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)
    }

    private fun test1Start() {}
    private fun measureTest1() {}
    override fun onBackPressed() {
        super.onBackPressed()
    }
}