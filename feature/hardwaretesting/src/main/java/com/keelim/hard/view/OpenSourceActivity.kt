package com.keelim.hard.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.keelim.hard.R
import kotlinx.android.synthetic.main.activity_opensource.*

class OpenSourceActivity : AppCompatActivity() {
    private lateinit var backPressCloseHandler: BackPressCloseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opensource)
        setSupportActionBar(toolbar)

        toolbar_layout.title = title
        toolbar_layout.setExpandedTitleTextAppearance(R.style.expand)
        toolbar_layout.setCollapsedTitleTextAppearance(R.style.collapsed)

        backPressCloseHandler = BackPressCloseHandler(this)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "오픈소스를 표시 합니다. ", Snackbar.LENGTH_LONG).show()
        }

    }

    override fun onBackPressed() {
        backPressCloseHandler.onBackPressed()
    }
}