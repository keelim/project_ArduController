package com.keelim.arducon.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.keelim.arducon.R
import com.keelim.arducon.databinding.ActivitySplashBinding
import com.keelim.arducon.ui.home.HomeFragment

import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*


class SplashActivity : AppCompatActivity(R.layout.activity_splash) {
    private lateinit var appUpdateManager: AppUpdateManager
    private lateinit var binding: ActivitySplashBinding

    var listener = object : PermissionListener {
        override fun onPermissionGranted() {
            Snackbar.make(binding.containerSplash, "모든 권한이 승인 되었습니다. ", Snackbar.LENGTH_SHORT).show()
        }

        override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
            Toast.makeText(this@SplashActivity, deniedPermissions.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        Toast.makeText(this, "welcome to arducon", Toast.LENGTH_SHORT).show()

        appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                    appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE, this, 2)
                Snackbar.make(binding.containerSplash, "업데이트를 시작합니다.", Snackbar.LENGTH_SHORT).show()
                popupSnackBarForCompleteUpdate()
            } else
                Snackbar.make(binding.containerSplash, "최신 버전 어플리케이션 사용해주셔서 감사합니다.", Snackbar.LENGTH_SHORT).show()
        }


        TedPermission.with(this)
                .setPermissionListener(listener)
                .setRationaleMessage("앱의 기능을 사용하기 위해서는 권한이 필요합니다.")
                .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있습니다.")
                .setPermissions(android.Manifest.permission.INTERNET,
                        android.Manifest.permission.BLUETOOTH,
                        android.Manifest.permission.BLUETOOTH_ADMIN)
                .check()

        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, HomeFragment::class.java).apply {
                startActivity(this)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }, 3000)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            when (resultCode) {
                RESULT_OK -> Snackbar.make(binding.containerSplash, "업데이트를 성공적으로 완료했습니다.", Snackbar.LENGTH_LONG).show()

                Activity.RESULT_CANCELED -> Snackbar.make(binding.containerSplash, "업데이트를 취소하였습니다.", Snackbar.LENGTH_LONG).show()

                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> Snackbar.make(binding.containerSplash, "시스템 오류가 발생했습니다.", Snackbar.LENGTH_LONG).show()
            }
        }
    }


    private fun popupSnackBarForCompleteUpdate() { // 이 부분은 다시 적용할 수 있을 것 같다.
        Snackbar.make(binding.containerSplash, "업데이트를 다운로드 하고 있습니다.", Snackbar.LENGTH_INDEFINITE).apply {
            setAction("RESTART") { appUpdateManager.completeUpdate() }
            show()
        }
    }

    override fun onBackPressed() {}
}

