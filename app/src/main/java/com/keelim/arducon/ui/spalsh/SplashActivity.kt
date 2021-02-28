package com.keelim.arducon.ui.spalsh

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.snackbar.Snackbar
import com.keelim.arducon.BuildConfig
import com.keelim.arducon.databinding.ActivitySplashBinding
import com.keelim.arducon.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val test = "ca -app-pub-3940256099942544/1033173712"
    private infix fun String.or(that: String): String = if (BuildConfig.DEBUG) this else that
    private var mInterstitialAd: InterstitialAd? = null

    private val permissions = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //권한이 있는 경우
        if (hasPermissions(permissions)) {
            showAd()
            goNext()
        } else {
            ActivityCompat.requestPermissions(this, permissions, MULTIPLE_PERMISSIONS)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            MULTIPLE_PERMISSIONS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(binding.root, "모든 권한이 승인 되었습니다. ", Snackbar.LENGTH_SHORT).show()
                    showAd()
                    goNext()
                } else {
                    // 하나라도 거부한다면.
                    AlertDialog.Builder(this)
                            .setTitle("앱 권한")
                            .setMessage("해당 앱의 원할한 기능을 이용하시려면 애플리케이션 정보>권한> 에서 모든 권한을 허용해 주십시오")
                            .setPositiveButton("권한설정") { dialog, which ->
                                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data = Uri.parse("package:" + applicationContext.packageName)
                                    startActivity(this)
                                    dialog.cancel()
                                }
                            }
                            .setNegativeButton("취소") { dialog, which -> dialog.cancel() }
                            .show()
                }
            }
        }
    }

    private fun hasPermissions(permissions: Array<String>): Boolean {
        permissions.forEach { permission ->
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }

    private fun goNext() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish() //앱을 종료한다.
        }
    }

    private fun showAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, test or "ca-app-pub-3115620439518585/4870874805", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Timber.d(adError.message)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Timber.d("Ad was loaded.")
                mInterstitialAd = interstitialAd

                if (mInterstitialAd != null) {
                    mInterstitialAd!!.show(this@SplashActivity)
                } else {
                    Timber.d("The interstitial ad wasn't ready yet.")
                }
            }
        })
    }

    companion object {
        const val MULTIPLE_PERMISSIONS = 8888
    }

    override fun onBackPressed() {}
}

