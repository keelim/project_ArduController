package com.keelim.hardware.view.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.Camera
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.keelim.hardware.R

import kotlinx.android.synthetic.main.fragment_flash.view.*


class FlashFragment : Fragment() {
    private lateinit var camera: Camera
    private lateinit var params: Camera.Parameters
    private lateinit var mediaPlayer: MediaPlayer
    private var isFlashOn = false
    private var hasFlash = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_flash, container, false)
        hasFlash =
            requireActivity().packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

        if (!hasFlash) {
            // device doesn't support flash
            // Show alert message and close the application
            val alert = AlertDialog.Builder(requireActivity())
                .setTitle("Error")
                .setMessage("Sorry, your device doesn't support flash light!")
                .setPositiveButton("OK") { _: DialogInterface?, _: Int ->
                    requireActivity().finish()
                }
                .create()
                .show()
        }

        toggleButtonImage(view)

        view.btnSwitch.setOnClickListener {
            if (isFlashOn)
                turnOffFlash() //turn off
            else
                turnOnFlash() // turn on flash
        }


        return view
    }


    private fun getCamera() {
        camera = Camera.open()
        params = camera.parameters

    }

    private fun turnOnFlash() {
        if (!isFlashOn) {
            // play sound
            playSound()
            params = camera.parameters
            params.flashMode = Camera.Parameters.FLASH_MODE_TORCH
            camera.parameters = params
            camera.startPreview()
            isFlashOn = true

            // changing button/switch image
//            toggleButtonImage(view)
        }
    }

    private fun turnOffFlash() {
        if (isFlashOn) {
            // play sound
            playSound()
            params = camera.parameters
            params.flashMode = Camera.Parameters.FLASH_MODE_OFF
            camera.parameters = params
            camera.stopPreview()
            isFlashOn = false

            // changing button/switch image
//            toggleButtonImage(view)
        }
    }

    /*
			 * Playing sound
			 * will play button toggle sound on flash on / off
			 * */
    private fun playSound() {
        mediaPlayer = if (isFlashOn) {
            MediaPlayer.create(requireActivity(), R.raw.light_switch_off)
        } else {
            MediaPlayer.create(requireActivity(), R.raw.light_switch_on)
        }.apply {
            setOnCompletionListener { obj: MediaPlayer -> obj.release() }
            start()
        }
    }

    private fun toggleButtonImage(view: View) {
        if (isFlashOn) {
            view.btnSwitch!!.setImageResource(R.drawable.btn_switch_on)
        } else {
            view.btnSwitch!!.setImageResource(R.drawable.btn_switch_off)
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