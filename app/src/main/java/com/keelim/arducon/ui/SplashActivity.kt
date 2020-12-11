package com.keelim.arducon.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.LoadAdError
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.keelim.arducon.BuildConfig
import com.keelim.arducon.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    private lateinit var appUpdateManager: AppUpdateManager
    private lateinit var binding: ActivitySplashBinding
    private lateinit var mInterstitialAd: InterstitialAd

    companion object {
        const val appupdate = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appUpdate()

        mInterstitialAd = InterstitialAd(this).apply {
            adUnitId = BuildConfig.API_KEY2
            adListener = (
                    object : AdListener() {
                        override fun onAdLoaded() {
                            Toast.makeText(this@SplashActivity, "onAdLoaded()", Toast.LENGTH_SHORT).show()
                            mInterstitialAd.show()
                        }

                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            val error = "domain: ${loadAdError.domain}, code: ${loadAdError.code}, " + "message: ${loadAdError.message}"
                            Toast.makeText(this@SplashActivity, "onAdFailedToLoad() with error $error", Toast.LENGTH_SHORT).show()
                            next()
                        }

                        override fun onAdClosed() {
                            next()
                        }
                    }
                    )

        }
        start()
    }

    private fun next() {
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this@SplashActivity, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }, 3000)
    }

    private fun start() {
        if (!mInterstitialAd.isLoading && !mInterstitialAd.isLoaded) {
            val adRequest = AdRequest.Builder().build()
            mInterstitialAd.loadAd(adRequest)
        }
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

