package com.keelim.arducon.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.keelim.arducon.R
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import kotlinx.android.synthetic.main.activity_intro.*


class SplashActivity : AppCompatActivity(R.layout.activity_intro) {
    private lateinit var appUpdateManager: AppUpdateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Snackbar.make(container_splash, "아두콘에 오신 것을 환영 합니다.", Snackbar.LENGTH_SHORT).show()

        AppCenter.start(application, getString(R.string.appcenter),
                Analytics::class.java, Crashes::class.java)



        appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                    appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                appUpdateManager.startUpdateFlowForResult(
                        // Pass the intent that is returned by 'getAppUpdateInfo()'.
                        appUpdateInfo,
                        // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                        AppUpdateType.FLEXIBLE,
                        // The current activity making the update request.
                        this,
                        // Include a request code to later monitor this update request.
                        2
                )
                Snackbar.make(container_splash, "업데이트를 시작합니다.", Snackbar.LENGTH_SHORT).show()
                popupSnackbarForCompleteUpdate()
            } else
                Snackbar.make(container_splash, "최신 버전 어플리케이션 사용해주셔서 감사합니다.", Snackbar.LENGTH_SHORT).show()
        }

        Handler().postDelayed({
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 3000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            when (resultCode) {
                RESULT_OK -> {
                    Snackbar.make(container_splash, "업데이트를 성공적으로 완료했습니다.", Snackbar.LENGTH_LONG)
                }
                Activity.RESULT_CANCELED -> {
                    Snackbar.make(container_splash, "업데이트를 취소하였습니다.", Snackbar.LENGTH_LONG)
                }
                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
                    Snackbar.make(container_splash, "시스템 오류가 발생했습니다.", Snackbar.LENGTH_LONG)
                }
            }
        }
    }


    private fun popupSnackbarForCompleteUpdate() {
        Snackbar.make(container_splash, "업데이트를 다운로드 하고 있습니다.", Snackbar.LENGTH_INDEFINITE).apply {
            setAction("RESTART") { appUpdateManager.completeUpdate() }
            setActionTextColor(resources.getColor(R.color.colorAccent, this@SplashActivity.theme))
            show()
        }
    }
}

