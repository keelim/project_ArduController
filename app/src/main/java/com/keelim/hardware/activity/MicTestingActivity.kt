package com.keelim.hardware.activity

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_mictesting.*
import java.util.*

class MicTestingActivity : AppCompatActivity(), OnInitListener {
    private var mTts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mictesting)
        // Initialize text-to-speech. This is an asynchronous operation.
        // The OnInitListener (second argument) is called after initialization completes.
        mTts = TextToSpeech(this,
                this // TextToSpeech.OnInitListener
        )

        // The button is disabled in the layout.
        // It will be enabled upon initialization of the TTS engine.

        mic_bt1!!.setOnClickListener {
            sayHello()
        }
    }

    public override fun onDestroy() {
        // Don't forget to shutdown!
        if (mTts != null) {
            mTts!!.stop()
            mTts!!.shutdown()
        }
        super.onDestroy()
    }

    // Implements TextToSpeech.OnInitListener.
    override fun onInit(status: Int) {
        // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // Note that a language may not be available, and the result will indicate this.
            val result = mTts!!.setLanguage(Locale.US)
            // Try this someday for some interesting results.
            // int result mTts.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Lanuage data is missing or the language is not supported.
                Log.e(TAG, "Language is not available.")
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
            Log.e(TAG, "Could not initialize TextToSpeech.")
        }
    }

    private fun sayHello() {
        // Select a random hello.
        val helloLength = HELLOS.size
        val hello = HELLOS[RANDOM.nextInt(helloLength)]
        mTts!!.speak(hello,
                TextToSpeech.QUEUE_FLUSH,  // Drop all pending entries in the playback queue.
                null)
    }

    companion object {
        private const val TAG = "TextToSpeechDemo"
        private val RANDOM = Random()
        private val HELLOS = arrayOf(
                "Your Speaker working very well",
                "Your Speaker working very well",
                "Your Speaker working very well",
                "Your Speaker working very well",
                "Your Speaker working very well"
        )
    }
}