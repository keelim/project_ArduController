package com.keelim.testing.test2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.testing.R
import com.keelim.testing.result.ResultActivity
import com.keelim.testing.utils.BackPressCloseHandler
import kotlinx.android.synthetic.main.activity_test2.*

class Test2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
        Toast.makeText(this, "테스트2 액티비티 입니다.", Toast.LENGTH_SHORT).show()

        btn_result2.setOnClickListener {
            test2Start()
        }
    }

    private fun test2Start() {
        Toast.makeText(this, "측정을 시작합니다.", Toast.LENGTH_SHORT).show()
        measureTest2()
    }

    private fun measureTest2() {
        Intent(this@Test2Activity, ResultActivity::class.java).apply {
            putExtra("test2", "data2")
            startActivity(this)
            finish()
        }
    }

    override fun onBackPressed() {
        BackPressCloseHandler(this).onBackPressed()
    }
}