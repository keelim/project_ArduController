package com.keelim.hard.view.custom

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
//import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.StorageReference
import com.keelim.hard.R
import com.keelim.hard.view.JsonActivity
import com.keelim.hard.view.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*
import java.io.File


class BottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener {
    private lateinit var file : File


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_dialog, container, false)

        view.share!!.setOnClickListener(this)
        view.check!!.setOnClickListener(this)
        view.parser!!.setOnClickListener(this)
        view.make!!.setOnClickListener(this)
        return view
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.make -> {
                (requireActivity() as (MainActivity)).apply {
                    makeJson()
                }
            }
            R.id.check -> checkingFile()
            R.id.parser -> {
                Intent(requireActivity(), JsonActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.share -> {
                AlertDialog.Builder(requireActivity())
                        .setMessage("정말로 파일을 업로드 하시겠습니까?")
                        .setPositiveButton("네") { _: DialogInterface, _: Int ->
                            if(file.exists()){
                                Toast.makeText(requireContext(), "업로드를 시작합니다.", Toast.LENGTH_SHORT).show()
//                                fileUpload()
                            } else {
                                Toast.makeText(requireContext(), "json 파일을 다시 확인 해주세요", Toast.LENGTH_SHORT).show()
                            }
                        }.create()
                        .show()
            }
        }
        dismiss()
    }

    companion object {
        val instance: BottomSheetDialog
            get() = BottomSheetDialog()
    }

    private fun checkingFile() {
        file = File(requireActivity().application.filesDir, getString(R.string.file))
        val message: String = if (file.exists()) "정상적으로 존재합니다. . " else "파일이 존재 하지 않습니다.  "
        Snackbar.make(main_container, message, Snackbar.LENGTH_SHORT).show()
    }

//    private fun fileUpload() { // json 올리는 버튼이 있을 것
//        val file = Uri.fromFile(File(requireActivity().application.filesDir.absolutePath + getString(R.string.file)))
//        val storage = FirebaseStorage.getInstance()
//
//        val jsonReference: StorageReference = storage.reference.child("data/" + file.lastPathSegment)
//        val uploadTask = jsonReference.putFile(file)
//
//        uploadTask.addOnFailureListener {
//
//        }.addOnSuccessListener {
//            val download = it.uploadSessionUri
//            Toast.makeText(requireContext(), download.toString(), Toast.LENGTH_SHORT).show()
//        }
//    }





}