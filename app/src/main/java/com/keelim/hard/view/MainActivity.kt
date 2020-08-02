package com.keelim.hard.view

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.keelim.hard.R
import com.keelim.hard.utils.ManageJson
import com.keelim.hard.view.custom.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.io.File
import java.io.OutputStream
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var sm: SensorManager
    private var isFabOpen: Boolean = false
    private lateinit var fabClose: Animation
    private lateinit var fabOpen: Animation
    private lateinit var list: MutableList<Sensor>
    private lateinit var ref: StorageReference
    private lateinit var file: File


    private lateinit var injectionFile: File


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        file = File(application.filesDir, getString(R.string.file))
        ref = FirebaseStorage.getInstance().reference



        floating1.setOnClickListener {
            toggleFab()
        }

        floating2.setOnClickListener {
            toggleFab()
            makeJson()
        }

        floating3.setOnClickListener {
            val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog.instance
            bottomSheetDialog.show(supportFragmentManager, "bottomSheet")
        }

        floating4.setOnClickListener {
            Intent(this, OpenSourceActivity::class.java).apply {
                startActivity(this)
            }
        }

        floating5.setOnClickListener{
            AlertDialog.Builder(this)
                    .setMessage("정말로 파일을 업로드 하시겠습니까?")
                    .setPositiveButton("네") { _: DialogInterface, _: Int ->
                        if (file.exists()) {
                            Toast.makeText(this, "파일을 전송 합니다.", Toast.LENGTH_SHORT).show()
                            uploadToServer()
                        } else {
                            Toast.makeText(this, "파일을 다시 확인 해주세요", Toast.LENGTH_SHORT).show()
                        }
                    }.create()
                    .show()

        }


        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawer_layout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)

        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        list = sm.getSensorList(Sensor.TYPE_ALL)

        val str = StringBuilder()
                .append("전체 센서 수: ").append(list.size).append("\n")

        for ((i, s) in list.withIndex()) {
            str.append("").append(i).append(" name: ").append(s.name).append("\n")
                    .append("power: ").append(s.power).append("\n").append("resolution: ").append(s.resolution).append("\n")
                    .append("range: ").append(s.maximumRange).append("\n").append("vendor: ").append(s.vendor).append("\n")
                    .append("min delay: ").append(s.minDelay).append("\n\n")
        }


        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close)

        floating1.text = "Adding"
        floating2.startAnimation(fabClose)
        floating3.startAnimation(fabClose)
        floating4.startAnimation(fabClose)
        floating5.startAnimation(fabClose)
    }

    private fun toggleFab() {
        if (isFabOpen) {
            floating1.text = "Adding"
            floating2.startAnimation(fabClose)
            floating3.startAnimation(fabClose)
            floating4.startAnimation(fabClose)
            floating5.startAnimation(fabClose)
            floating2.isClickable = false
            floating3.isClickable = false
            floating4.isClickable = false
            floating5.isClickable = false
            isFabOpen = false
        } else {
            floating1.text = "close"
            floating2.startAnimation(fabOpen)
            floating3.startAnimation(fabOpen)
            floating4.startAnimation(fabOpen)
            floating5.startAnimation(fabOpen)
            floating2.isClickable = true
            floating3.isClickable = true
            floating4.isClickable = true
            floating5.isClickable = true
            isFabOpen = true
        }
    }

    private fun makeJson() {
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        return boolean
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drawer, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun uploadToServer() {
        //if there is a file to upload
        val filePath = Uri.fromFile(File(application.filesDir, getString(R.string.file)))
        //displaying a progress dialog while upload is going on
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Uploading...")
        progressDialog.show()

        val riversRef: StorageReference = ref.child("jsonFile/sample.json")
        riversRef.putFile(filePath)
                .addOnSuccessListener {
                    progressDialog.dismiss()
                    Toast.makeText(this, "File Uploaded", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { exception ->
                    progressDialog.dismiss()
                    Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener { taskSnapshot ->

                    val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                    progressDialog.setMessage("Uploaded " + progress.toInt() + "%...")
                }
    }




}