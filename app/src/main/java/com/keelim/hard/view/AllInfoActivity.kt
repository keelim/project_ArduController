package com.keelim.hard.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.keelim.hard.R
import kotlinx.android.synthetic.main.activity_all_info.*

class AllInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_info)
        setSupportActionBar(toolbar)
        toolbar.title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "모든 정보를 저장을 하고 출력합니다.", Snackbar.LENGTH_LONG).show()
        }
    }
}