package com.keelim.arducon.ui

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.material.snackbar.Snackbar
import com.keelim.arducon.BuildConfig
import com.keelim.arducon.R
import com.keelim.arducon.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var interstitialAd: InterstitialAd
    private val test = "ca -app-pub-3940256099942544/1033173712"
    private infix fun String.or(that: String): String = if (BuildConfig.DEBUG) this else that
    private lateinit var settings: SharedPreferences

    private val permissions = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addShortcut()

        //권한이 있는 경우
        if (hasPermissions(permissions)) {
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

                    interstitialAd = InterstitialAd(this@SplashActivity)
                    interstitialAd.adUnitId = test or "ca-app-pub-3115620439518585/4870874805"
                    interstitialAd.adListener = object : AdListener() {
                        override fun onAdLoaded() {
                            interstitialAd.show()
                        }

                        override fun onAdClosed() {}
                        override fun onAdFailedToLoad(errorCode: Int) {
                            Toast.makeText(this@SplashActivity, "ad load fail $errorCode", Toast.LENGTH_SHORT).show()
                            Timber.e("Error code")
                        }
                    } //전면광고 셋팅
                    interstitialAd.loadAd(AdRequest.Builder().build())

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

    override fun onBackPressed() {}

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

    private fun addShortcut() {
        settings = getSharedPreferences(PREF_FIRST_START, 0)

        if (settings.getBoolean("AppFirstLaunch", true)) {  // 아이콘이 두번 추가 안되도록 하기 위해서 필요한 체크입니다.
            settings.edit().putBoolean("AppFirstLaunch", false).apply()

            if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
                val shortcutInfo = ShortcutInfoCompat.Builder(this, "#1")
                        .setIntent(Intent(this, SplashActivity::class.java).setAction(Intent.ACTION_MAIN))
                        .setShortLabel(getString(R.string.app_name)) //  아이콘에 같이 보여질 이름
//                        .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_launcher)) //아이콘에 보여질 이미지
                        .build()

                ShortcutManagerCompat.requestPinShortcut(this, shortcutInfo, null)
                Toast.makeText(this, "홈 화면에 바로가기를 추가하였습니다. ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val PREF_FIRST_START = "AppFirstLaunch"
        const val MULTIPLE_PERMISSIONS = 8888
    }
}

