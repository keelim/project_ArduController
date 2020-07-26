package com.keelim.hardware.view

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_mic.*

import java.util.*

class MicActivity : AppCompatActivity(R.layout.activity_mic), OnInitListener {
    private lateinit var speech: TextToSpeech
    private val random = Random()
    private val hellos = arrayOf(
        "Your Speaker working very well",
        "Your Speaker working very well",
        "Your Speaker working very well",
        "Your Speaker working very well",
        "Your Speaker working very well"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize text-to-speech. This is an asynchronous operation.
        // The OnInitListener (second argument) is called after initialization completes.
        speech = TextToSpeech(this, this) // TextToSpeech.OnInitListener

        mic_bt1!!.setOnClickListener {
            sayHello()
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        speech.stop()
        speech.shutdown()
    }

    // Implements TextToSpeech.OnInitListener.
    override fun onInit(status: Int) {
        // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // Note that a language may not be available, and the result will indicate this.
            val result = speech.setLanguage(Locale.US)
            // Try this someday for some interesting results.
            // int result mTts.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Lanuage data is missing or the language is not supported.
            } else {
                // Check the documentation for other possible result codes.
                // For example, the language may be available for the locale,
                // but not for the specified country and variant.
                // The TTS engine has been successfully initialized.
                // Allow the user to press the button for the app to speak again.
                mic_bt1!!.isEnabled = true
                // Greet the user.
                sayHello()
            }
        } else {
            // Initialization failed.
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sayHello() {
        val helloLength = hellos.size
        val hello = hellos[random.nextInt(helloLength)]
        speech.speak(hello, TextToSpeech.QUEUE_FLUSH, null) // Drop all pending entries in the playback queue.
    }

}