package com.keelim.testing.test2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.testing.R

class Test2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
    }

    private fun test2Start() {}
    private fun measureTest2() {}
    override fun onBackPressed() {
        super.onBackPressed()
    }
}