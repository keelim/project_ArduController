package com.keelim.arducon.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.arducon.R
import kotlinx.android.synthetic.main.activity_controller.*

class ControllerActivity : AppCompatActivity(R.layout.activity_controller) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "컨트롤러 설정 입니다.", Toast.LENGTH_SHORT).show()

        center_bt.setOnClickListener {
            Toast.makeText(this, "center 버튼을 클릭하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}




