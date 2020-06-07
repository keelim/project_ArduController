package com.keelim.testing.test1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.keelim.testing.R
import com.keelim.testing.result.ResultActivity
import com.keelim.testing.utils.BackPressCloseHandler
import kotlinx.android.synthetic.main.activity_test1.*

class Test1Activity : AppCompatActivity() {
    lateinit var test1Adapter: Test1Adapter
    var result_array = ArrayList<Long>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)

        Toast.makeText(this, "테스트1 액티비티 입니다.", Toast.LENGTH_SHORT).show()
        test1Adapter = Test1Adapter(arrayListOf())
        test1_progress.visibility = View.GONE


        btn_result1.setOnClickListener {
            switch()
            test1Start()
            switch()
        }
    }

    private fun test1Start() {
        Snackbar.make(test1_container, "테스트1를 시작 합니다.", Snackbar.LENGTH_SHORT).show()
        measureTest1()

    }

    private fun measureTest1() {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        for (x in 0..10000) {

            val start = System.currentTimeMillis()
            Log.d("test1_start", "dialog start time: $start")

            val alert = AlertDialog.Builder(this)
                .create()
            alert.show()

            Thread.sleep(100)
            alert.dismiss()
            val end = System.currentTimeMillis()
            Log.d("test1_start", "dialog end time: $end")

            val time = end - start
            Log.d("test1 time", "test1 time:$time")

            val meanTime = time * 1000
            Toast.makeText(this, "측정 시간 입니다. $meanTime", Toast.LENGTH_SHORT).show()
            Thread.sleep(100)
            result_array.add(time);
        }

        Snackbar.make(test1_container, "테스트를 종료 합니다. ", Snackbar.LENGTH_SHORT).show()
        Thread.sleep(100);

        endTest()
    }

    private fun endTest() {
        Intent(this, ResultActivity::class.java).apply {
            putExtra("test1", "data1")
            putExtra("result", result_array)
            startActivity(this)
            finish()
        }
    }

    override fun onBackPressed() {
        BackPressCloseHandler(this).onBackPressed()
    }

    private fun switch(){
        if (test1_progress.visibility == View.GONE) {
            test1_progress.visibility = View.VISIBLE;
        } else {
            test1_progress.visibility = View.GONE;
        }
    }

}