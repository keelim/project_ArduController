package com.keelim.testing.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.keelim.testing.R
import com.keelim.testing.test1.Test1Activity
import com.keelim.testing.test2.Test2Activity
import com.keelim.testing.utils.BackPressCloseHandler
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            Toast.makeText(this@MainActivity, "모든 권한이 승인 되었습니다.", Toast.LENGTH_SHORT).show()
        }

        override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
            Toast.makeText(this@MainActivity, "모든 권한이 승인 되지 않았습니다. 종료합니다.", Toast.LENGTH_SHORT)
                .show()
            Thread.sleep(3000)
            finish()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "권한 확인 중입니다.", Toast.LENGTH_SHORT).show()

        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setDeniedMessage("권한을 승인하지 않으시면 어플리케이션이 종료됩니다.")
            .setPermissions(android.Manifest.permission.INTERNET)
            .check()



        val list = ArrayList<String>()
        for (i in 0..99) {
            list.add(String.format("TEXT %d", i))
        }

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        val recyclerView = findViewById<RecyclerView>(R.id.main_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        val adapter = MainAdapter(list)
        recyclerView.adapter = adapter

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_test1 -> {
                val test1 = Intent(this, Test1Activity::class.java)
                startActivity(test1)
                finish()
            }
            R.id.btn_test2 -> {
                val test2 = Intent(this, Test2Activity::class.java)
                startActivity(test2)
                finish()
            }
        }
    }

    override fun onBackPressed() {
        BackPressCloseHandler(this).onBackPressed()
    }
}