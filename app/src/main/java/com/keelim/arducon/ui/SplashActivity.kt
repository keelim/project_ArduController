package com.keelim.arducon.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.keelim.arducon.BuildConfig
import com.keelim.arducon.databinding.ActivitySplashBinding
import java.util.*


class SplashActivity : AppCompatActivity() {
    private lateinit var appUpdateManager: AppUpdateManager
    private lateinit var binding: ActivitySplashBinding
    private lateinit var interstitialAd: InterstitialAd
    private val test = "ca -app-pub-3940256099942544/1033173712"
    private infix fun String.or(that: String): String = if (BuildConfig.DEBUG) this else that

    companion object {
        const val appupdate = 2
    }

    private var listener = object : PermissionListener {
        override fun onPermissionGranted() {
            Snackbar.make(binding.root, "모든 권한이 승인 되었습니다. ", Snackbar.LENGTH_SHORT).show()

            interstitialAd = InterstitialAd(this@SplashActivity)
            interstitialAd.adUnitId = test or BuildConfig.API_KEY2
            interstitialAd.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    interstitialAd.show()
                }

                override fun onAdClosed() {}

                override fun onAdFailedToLoad(errorCode: Int) {
                    Toast.makeText(this@SplashActivity, "ad load fail", Toast.LENGTH_SHORT).show()
                    Log.e("ADMOB", errorCode.toString())
                }
            } //전면광고 셋팅
            interstitialAd.loadAd(AdRequest.Builder().build())

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish() //앱을 종료한다.
            }, 300)
        }

        override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
            Handler(Looper.getMainLooper()).postDelayed({
                Toast.makeText(this@SplashActivity, deniedPermissions.toString(), Toast.LENGTH_SHORT).show()
                finish()
            }, 3000)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appUpdate()

        TedPermission.with(this)
                .setPermissionListener(listener)
                .setRationaleMessage("앱의 기능을 사용하기 위해서는 권한이 필요합니다.")
                .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있습니다.")
                .setPermissions(
                        Manifest.permission.INTERNET,
                        Manifest.permission.BLUETOOTH,
                        Manifest.permission.BLUETOOTH_ADMIN
                )
                .check()
    }

    private fun appUpdate() {
        appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE, this, appupdate)
                Snackbar.make(binding.containerSplash, "업데이트를 시작합니다.", Snackbar.LENGTH_SHORT).show()
                popupSnackBarForCompleteUpdate()
            } else
                Snackbar.make(binding.containerSplash, "최신 버전 어플리케이션 사용해주셔서 감사합니다.", Snackbar.LENGTH_SHORT).show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == appupdate) {
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

