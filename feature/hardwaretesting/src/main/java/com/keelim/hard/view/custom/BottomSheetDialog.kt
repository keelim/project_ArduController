package com.keelim.hard.view.custom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.keelim.hard.R
import com.keelim.hard.view.JsonActivity
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*
import java.io.File

class BottomSheetDialog() : BottomSheetDialogFragment(), View.OnClickListener {
    private lateinit var file: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        file = File(requireActivity().application.filesDir, getString(R.string.file))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_dialog, container, false)

        view.check!!.setOnClickListener(this)
        view.parser!!.setOnClickListener(this)
        return view
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.check -> checkingFile()
            R.id.parser -> {
                Intent(requireContext(), JsonActivity::class.java).apply {
                    startActivity(this)
                }
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
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun fileUpload() { // json 올리는 버튼이 있을 것
        val file = Uri.fromFile(File(requireActivity().application.filesDir.absolutePath + getString(R.string.file)))
        val storage = FirebaseStorage.getInstance()

        val jsonReference: StorageReference = storage.reference.child("data/" + file.lastPathSegment)
        val uploadTask = jsonReference.putFile(file)

        uploadTask.addOnFailureListener {

        }.addOnSuccessListener {
            val download = it.uploadSessionUri
            Toast.makeText(requireContext(), download.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}