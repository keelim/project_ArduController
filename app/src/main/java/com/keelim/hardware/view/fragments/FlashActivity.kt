package com.keelim.hardware.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.Camera
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_flash.*

class FlashActivity : AppCompatActivity(R.layout.activity_flash) {
    private lateinit var camera: Camera
    private lateinit var params: Camera.Parameters
    private lateinit var mediaPlayer: MediaPlayer
    private var isFlashOn = false
    private var hasFlash = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        * First check if device is supporting flashlight or not
        hasFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

        if (!hasFlash) {
            // device doesn't support flash
            // Show alert message and close the application
            val alert = AlertDialog.Builder(this@FlashActivity)
                .setTitle("Error")
                .setMessage("Sorry, your device doesn't support flash light!")
                .setPositiveButton("OK") { _: DialogInterface?, _: Int ->
                    finish()
                }
                .create()

            alert.show()
        }

        // get the camera
        getCamera()
        // displaying button image
        toggleButtonImage()

        /*
        * Switch button click event to toggle flash on/off
        */
        btnSwitch.setOnClickListener {
            if (isFlashOn)
                turnOffFlash() //turn off
            else
                turnOnFlash() // turn on flash
        }

    }

    /*
			 * Get the camera
			 */


    private fun getCamera() {
        camera = Camera.open()
        params = camera.parameters

    }

    /*
			 * Turning On flash
			 */
    private fun turnOnFlash() {
        if (!isFlashOn) {
            // play sound
            playSound()
            params = camera!!.parameters
            params!!.flashMode = Camera.Parameters.FLASH_MODE_TORCH
            camera!!.parameters = params
            camera!!.startPreview()
            isFlashOn = true

            // changing button/switch image
            toggleButtonImage()
        }
    }

    /*
			 * Turning Off flash
			 */
    private fun turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return
            }
            // play sound
            playSound()
            params = camera!!.parameters
            params!!.flashMode = Camera.Parameters.FLASH_MODE_OFF
            camera!!.parameters = params
            camera!!.stopPreview()
            isFlashOn = false

            // changing button/switch image
            toggleButtonImage()
        }
    }

    /*
			 * Playing sound
			 * will play button toggle sound on flash on / off
			 * */
    private fun playSound() {
        mediaPlayer = if (isFlashOn) {
            MediaPlayer.create(this@FlashActivity, R.raw.light_switch_off)
        } else {
            MediaPlayer.create(this@FlashActivity, R.raw.light_switch_on)
        }.apply {
            setOnCompletionListener { obj: MediaPlayer -> obj.release() }
            start()
        }
    }

    /*
			 * Toggle switch button images
			 * changing image states to on / off
			 * */
    private fun toggleButtonImage() {
        if (isFlashOn) {
            btnSwitch!!.setImageResource(R.drawable.btn_switch_on)
        } else {
            btnSwitch!!.setImageResource(R.drawable.btn_switch_off)
        }
    }

    override fun onPause() {
        super.onPause()
        // on pause turn off the flash
        turnOffFlash()
    }

    override fun onResume() {
        super.onResume()
        // on resume turn on the flash
        if (hasFlash) turnOnFlash()
    }

    override fun onStart() {
        super.onStart()
        // on starting the app get the camera params
        getCamera()
    }

    override fun onStop() {
        super.onStop()
        // on stop release the camera
        camera.release()
    }
}