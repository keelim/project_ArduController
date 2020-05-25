package com.keelim.testing.test2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.testing.R
import com.keelim.testing.result.ResultActivity
import com.keelim.testing.utils.BackPressCloseHandler

class Test2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
    }

    private fun test2Start() {

    }

    private fun measureTest2() {
        var test2Intent = Intent(this@Test2Activity, ResultActivity::class.java).apply {
            putExtra("test2", "data2")
            startActivity(this)
            finish()
        }
    }

    override fun onBackPressed() {
        BackPressCloseHandler(this).onBackPressed()
    }
}