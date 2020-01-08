package com.keelim.arducon.controller

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.arducon.R

class ControllerActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controller)
        Toast.makeText(this, "컨트롤러 설정 입니다.", Toast.LENGTH_SHORT).show()

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.bt_center -> Toast.makeText(this, "center 버튼을 클릭하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }


}




