package com.keelim.hard.utils

import android.app.Activity
import android.widget.Toast


class BackPressCloseHandler(private val activity: Activity) { //객체를 만들어야 한다.
    private var backKeyPressedTime: Long = 0
    private lateinit var toast: Toast

    fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            showGuide()
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish()
            toast.cancel()
        }
    }

    private fun showGuide() {
        toast = Toast.makeText(
                activity, "one more please back button",
                Toast.LENGTH_SHORT
        )
        toast.show()
    }

}