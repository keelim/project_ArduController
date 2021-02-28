/*
 * Designed and developed by 2020 keelim (Jaehyun Kim)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.keelim.hard.ui.custom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.keelim.common.toast
import com.keelim.hard.R
import com.keelim.hard.databinding.BottomSheetDialogBinding
import com.keelim.hard.ui.JsonActivity
import java.io.File

class BottomSheetDialog : BottomSheetDialogFragment() {
  private lateinit var file: File
  private lateinit var binding: BottomSheetDialogBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate<BottomSheetDialogBinding>(inflater, R.layout.bottom_sheet_dialog, container, false)
    file = File(requireActivity().application.filesDir, getString(R.string.file))
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    binding.check.setOnClickListener {
      checkingFile()
      dismiss()
    }

    binding.parser.setOnClickListener {
      Intent(requireContext(), JsonActivity::class.java).apply {
        startActivity(this)
      }
      dismiss()
    }
  }

  private fun checkingFile() {
    file = File(requireActivity().application.filesDir, getString(R.string.file))
    val message: String = if (file.exists()) "정상적으로 존재합니다. . " else "파일이 존재 하지 않습니다.  "
    requireActivity().toast(message)
  }

  private fun fileUpload() { // json 올리는 버튼이 있을 것
    val file = Uri.fromFile(File(requireActivity().application.filesDir.absolutePath + getString(R.string.file)))
    val storage = FirebaseStorage.getInstance()

    val jsonReference: StorageReference = storage.reference.child("data/" + file.lastPathSegment)
    val uploadTask = jsonReference.putFile(file)

    uploadTask.addOnFailureListener {
    }.addOnSuccessListener {
      val download = it.uploadSessionUri
      requireActivity().toast(download.toString())
    }
  }

  companion object {
    val instance: BottomSheetDialog
      get() = BottomSheetDialog()
  }
}
