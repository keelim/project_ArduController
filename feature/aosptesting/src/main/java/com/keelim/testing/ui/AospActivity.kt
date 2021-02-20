package com.keelim.testing.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.testing.R
import com.keelim.testing.ui.addwindowtest.AddWindowTestActivity
import com.keelim.testing.ui.handlertest.HandlerTestActivity
import kotlinx.android.synthetic.main.activity_main.*

class AospActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test1.setOnClickListener {
            Intent(this, AddWindowTestActivity::class.java).apply {
                startActivity(this)
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
                finish()
            }
        }

        btn_test2.setOnClickListener {
            Intent(this, HandlerTestActivity::class.java).apply {
                startActivity(this)
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
                finish()
            }
        }
    }
}