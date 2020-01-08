package com.keelim.arducon.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.arducon.R
import com.keelim.arducon.fragments.SettingFragment

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) { //fragment 설정을 위한 activity
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, SettingFragment())
                .commit()
    }
}