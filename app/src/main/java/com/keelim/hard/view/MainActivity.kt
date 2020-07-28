package com.keelim.hard.view

//import com.google.firebase.ktx.Firebase
//import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.StorageReference
//import com.google.firebase.storage.ktx.storage
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.ScrollingMovementMethod
import android.view.KeyEvent
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.keelim.hard.MyApplication
import com.keelim.hard.R
import com.keelim.hard.model.FrKeyEventListener
import com.keelim.hard.utils.ManageJson
import com.keelim.hard.view.custom.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.io.File
import java.io.OutputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var list: MutableList<Sensor>
    private lateinit var sm: SensorManager
    private lateinit var frKeyEventListener: FrKeyEventListener

    //    private lateinit var storage: FirebaseStorage
//    private lateinit var ref: StorageReference
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


//        storage = Firebase.storage //init
//        ref = storage.reference

        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        list = sm.getSensorList(Sensor.TYPE_ALL)

        val i = 0
        val str = StringBuilder()
                .append("전체 센서 수: ").append(list.size).append("\n")

        for (s in list) {
            str.append("").append(i).append(" name: ").append(s.name).append("\n")
                    .append("power: ").append(s.power).append("\n").append("resolution: ").append(s.resolution).append("\n")
                    .append("range: ").append(s.maximumRange).append("\n").append("vendor: ").append(s.vendor).append("\n")
                    .append("min delay: ").append(s.minDelay).append("\n\n")

        }

        after_text.movementMethod = ScrollingMovementMethod()
        after_text.text = str.toString()

        floating.setOnClickListener {
            val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog.instance
            bottomSheetDialog.show(supportFragmentManager, "bottomSheet")
        }

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_setting, R.id.nav_acc, R.id.nav_battery, R.id.nav_bluetooth, R.id.nav_display, R.id.nav_headphone,
                R.id.nav_light, R.id.nav_magnetic, R.id.nav_proximity, R.id.nav_pressure, R.id.nav_sound, R.id.nav_system,
                R.id.nav_tele, R.id.nav_touch, R.id.nav_wifi, R.id.nav_slideshow), drawer_layout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
        
    }

    fun makeJson() {
        val jsonString = ManageJson.makeList(list)
        if (makeFile(jsonString)) Toast.makeText(this, "JSON 파일 성공적으로 만들었습니다.", Toast.LENGTH_SHORT).show()
        else Toast.makeText(this, "실패하였습니다.", Toast.LENGTH_SHORT).show()
    }


    private fun makeFile(s: String?): Boolean {
        val filename = getString(R.string.file)
        val outputStream: OutputStream
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            outputStream.write(s!!.toByteArray(Charset.defaultCharset()))
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val file = File(application.filesDir, filename)
        val message: String
        val boolean: Boolean
        if (file.exists()) {
            message = "정상적으로 완성되었습니다. "
            boolean = true
        } else {
            message = "에러가 나서 다시 실행해주세요 "
            boolean = false
        }
        Snackbar.make(main_container, message, Snackbar.LENGTH_SHORT).show()

        return boolean
    }

    private fun startProgress() {
        progressOn("Loading...")
        Handler(Looper.myLooper()!!).postDelayed(Runnable { progressOff() }, 3500)
    }

    private fun progressOn() {
        MyApplication.getInstance().progressON(this, null)
    }

    private fun progressOn(message: String?) {
        MyApplication.getInstance().progressON(this, message)
    }

    private fun progressOff() {
        MyApplication.getInstance().progressOFF()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drawer, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    fun setFrKeyEventListener(fragmentKeyEventListener: FrKeyEventListener) {
        this.frKeyEventListener = fragmentKeyEventListener;
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        frKeyEventListener.FrtKeyEvent(event)
        return true;
    }


}