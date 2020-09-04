package com.keelim.testing.ui.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.keelim.testing.R
import kotlinx.android.synthetic.main.activity_result.*
import java.util.*

class ResultActivity : AppCompatActivity() {
    private lateinit var resultArray: ArrayList<Long>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultArray = intent.getSerializableExtra("result") as ArrayList<Long>
        // array List 를 받는다.

        result_recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ResultActivity)
            adapter = ResultAdapter(resultArray)
        }
    }
}

