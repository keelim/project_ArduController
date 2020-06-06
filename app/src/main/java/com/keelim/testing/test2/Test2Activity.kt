package com.keelim.testing.test2

import MyService
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.keelim.testing.R
import com.keelim.testing.result.ResultActivity
import com.keelim.testing.utils.BackPressCloseHandler
import kotlinx.android.synthetic.main.activity_test2.*

class Test2Activity : AppCompatActivity() {
    lateinit var test2Adapter: Test2Adapter
    var result_array = ArrayList<Long>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        test2_progress.visibility = View.GONE
        Toast.makeText(this, "테스트2 액티비티 입니다.", Toast.LENGTH_SHORT).show()

        test2Adapter = Test2Adapter(arrayListOf())

        btn_result2.setOnClickListener {
            test2Start()
        }
    }

    private fun test2Start() {
        Snackbar.make(test2_container, "테스트2를 시작 합니다.", Snackbar.LENGTH_SHORT).show()
        test2_progress.visibility = View.VISIBLE
        measureTest2()
        test2_progress.visibility = View.GONE
    }

    private fun measureTest2() {
        for (x in 1..10000) {
            val start = System.currentTimeMillis()
            Log.d("test2_start", "dialog start time: $start")

            startService(Intent(this@Test2Activity, MyService::class.java))
            Thread.sleep(100)
            stopService(Intent(this@Test2Activity, MyService::class.java))

            val end = System.currentTimeMillis()
            Log.d("test1_start", "dialog end time: $end")

            val time = end - start
            val meanTime = time * 1000
            Toast.makeText(this, "측정 시간 입니다. $meanTime", Toast.LENGTH_SHORT).show()
            Log.d("test1 time", "test1 time:$time")
            result_array.add(time);
            Thread.sleep(100)
        }

        Snackbar.make(test2_container, "테스트를 종료 합니다. ", Snackbar.LENGTH_SHORT).show()
        Thread.sleep(1000);

        endTest()
    }

    private fun endTest() {
        Intent(this, ResultActivity::class.java).apply {
            putExtra("test2", "data2")
            putExtra("result", result_array)
            startActivity(this)
            finish()
        }
    }

    override fun onBackPressed() {
        BackPressCloseHandler(this).onBackPressed()
    }
}