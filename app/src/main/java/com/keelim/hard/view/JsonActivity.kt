package com.keelim.hard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.keelim.hard.R
import kotlinx.android.synthetic.main.activity_json.*
import java.io.IOException
import java.nio.charset.Charset

class JsonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json)

        json_btn.setOnClickListener {
            json_tv.text = getJsonString()
        }
    }

    private fun getJsonString(): String {
        var json = ""

        try {
            var input = openFileInput(application.filesDir.absolutePath + getString(R.string.file))
            val fileSize: Int = input.available()
            var buffer = ByteArray(fileSize)
            input.read(buffer)
            input.close()

            json = String(buffer, Charset.defaultCharset())
        } catch (e: IOException) {
            Toast.makeText(this, "파일 만드는데 문제가 생겼습니다. ", Toast.LENGTH_SHORT).show()
        }
        return json
    }


}