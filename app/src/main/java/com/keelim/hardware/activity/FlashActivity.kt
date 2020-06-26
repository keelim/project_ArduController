package com.keelim.hardware.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.Camera
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_flash.*

class FlashActivity : AppCompatActivity() {
    private var camera: Camera? = null
    private var isFlashOn = false
    private var hasFlash = false
    private var params: Camera.Parameters? = null
    private var mp: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash)
        // flash switch button


        /*
				 * First check if device is supporting flashlight or not
				 */
        hasFlash = this.packageManager
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        if (!hasFlash) {
            // device doesn't support flash
            // Show alert message and close the application
            val alert = AlertDialog.Builder(this@FlashActivity)
                    .create()
            alert.setTitle("Error")
            alert.setMessage("Sorry, your device doesn't support flash light!")
            alert.setButton("OK") { dialog: DialogInterface?, which: Int ->
                // closing the application
                finish()
            }
            alert.show()
            return
        }

        // get the camera
        getCamera()

        // displaying button image
        toggleButtonImage()

        /*
				 * Switch button click event to toggle flash on/off
				 */
        btnSwitch.setOnClickListener {
            if (isFlashOn) {
                // turn off flash
                turnOffFlash()
            } else {
                // turn on flash
                turnOnFlash()
            }
        }
    }

    /*
			 * Get the camera
			 */
    private fun getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open()
                params = camera!!.parameters
            } catch (e: RuntimeException) {

            }
        }
    }

    /*
			 * Turning On flash
			 */
    private fun turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return
            }
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
        mp = if (isFlashOn) {
            MediaPlayer.create(this@FlashActivity, R.raw.light_switch_off)
        } else {
            MediaPlayer.create(this@FlashActivity, R.raw.light_switch_on)
        }

        mp!!.setOnCompletionListener { obj: MediaPlayer -> obj.release() }
        mp!!.start()
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
        if (camera != null) {
            camera!!.release()
            camera = null
        }
    }
}