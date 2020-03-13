package com.keelim.arducon.view.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.arducon.R

class SettingActivity : AppCompatActivity(R.layout.activity_setting) {

    override fun onCreate(savedInstanceState: Bundle?) { //fragment 설정을 위한 activity
        super.onCreate(savedInstanceState)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, SettingFragment())
                .commit()
    }
}