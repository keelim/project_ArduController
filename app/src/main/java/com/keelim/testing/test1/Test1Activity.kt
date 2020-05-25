package com.keelim.testing.test1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.testing.R
import com.keelim.testing.result.ResultActivity
import com.keelim.testing.utils.BackPressCloseHandler

class Test1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)
    }

    private fun test1Start() {

    }

    private fun measureTest1() {
        var intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("test1", "data1")
            startActivity(this)
            finish()
        }
    }

    override fun onBackPressed() {
        BackPressCloseHandler(this).onBackPressed()
    }

//    fun setup() {
//        val job = GlobalScope.launch(Dispatchers.Main) { // launch coroutine in the main thread
//            delay(1000)
//            for (i in 10 downTo 1) { // countdown from 10 to 1
//                tv_message.text = "Countdown $i ..." // update text
//                delay(500) // wait half a second
//            }
//            tv_message.text = "Done!"
//        }
//        fab.setOnClickListener {
//            job.cancel() // cancel coroutine on click
//        }
//    }

}