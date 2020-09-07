package com.keelim.testing.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.keelim.testing.R
import com.keelim.testing.ui.addwindowtest.AddWindowTestActivity
import com.keelim.testing.ui.handlertest.HandlerTestActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_test1.*

class MainActivity : AppCompatActivity() {


    private val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            Toast.makeText(this@MainActivity, "모든 권한이 승인 되었습니다.", Toast.LENGTH_SHORT).show()
        }

        override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
            Handler(Looper.getMainLooper()).postDelayed(
                    {
                        Toast.makeText(this@MainActivity, "모든 권한이 승인 되지 않았습니다. 종료합니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }, 3000
            )
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("권한을 승인하지 않으시면 어플리케이션이 종료됩니다.")
                .setPermissions(android.Manifest.permission.INTERNET)
                .check()

        btn_test1.setOnClickListener {
            Intent(this, AddWindowTestActivity::class.java).apply {
                startActivity(this)
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
                finish()
            }
        }

        btn_result2.setOnClickListener {
            Intent(this, HandlerTestActivity::class.java).apply {
                startActivity(this)
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
                finish()
            }
        }

    }
}