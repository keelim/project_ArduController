package com.keelim.testing.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.keelim.testing.R
import com.keelim.testing.ui.test1.Test1Activity
import com.keelim.testing.ui.test2.Test2Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val ACTIONMANAGEOVERLAYPERMISSIONREQUESTCODE = 1


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

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("권한을 승인하지 않으시면 어플리케이션이 종료됩니다.")
                .setPermissions(android.Manifest.permission.INTERNET)
                .setPermissions(android.Manifest.permission.SYSTEM_ALERT_WINDOW)
                .setPermissions(android.Manifest.permission.FOREGROUND_SERVICE)
                .check()

        overlayCheck()

        btn_test1.setOnClickListener {
            Intent(this, Test1Activity::class.java).apply {
                startActivity(this)
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
                finish()
            }
        }

        btn_test2.setOnClickListener {
            Intent(this, Test2Activity::class.java).apply {
                startActivity(this)
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
                finish()
            }
        }

    }

    private fun overlayCheck() {
        if (!Settings.canDrawOverlays(this)) {
            Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName")).apply {
                startActivityForResult(this, ACTIONMANAGEOVERLAYPERMISSIONREQUESTCODE)
            }
        } else {
            Toast.makeText(this, "오버레이 권한이 승인 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTIONMANAGEOVERLAYPERMISSIONREQUESTCODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "권한이 승인 되었습니다.", Toast.LENGTH_SHORT).show()
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "재시작 합니다. ", Toast.LENGTH_SHORT).show()
                finish()
                startActivity(Intent(this, SplashActivity::class.java))
            }
        }
    }
}