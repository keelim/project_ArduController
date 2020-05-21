package com.keelim.testing.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.testing.R
import com.keelim.testing.test1.Test1Activity
import com.keelim.testing.test2.Test2Activity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "버튼을 입력을 해주세요", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.btn_test1 -> {
                val intent = Intent(this, Test1Activity::class.java)
                startActivity(intent)
            }
            R.id.btn_test2 -> {
                val intent2 = Intent(this, Test2Activity::class.java)
                startActivity(intent2)
            }
        }
    }
}