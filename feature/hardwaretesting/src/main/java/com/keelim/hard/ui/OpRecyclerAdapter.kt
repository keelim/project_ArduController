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
package com.keelim.hard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.keelim.aio.Open.OpenItem
import com.keelim.hard.R

class OpRecyclerAdapter : RecyclerView.Adapter<OpRecyclerAdapter.ViewHolder>() {
  private var mData: MutableList<OpenItem> = arrayListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

  class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_openview, parent, false)) {
    fun onBind(data: OpenItem) {
      val openText1: TextView = itemView.findViewById(R.id.open_tv1)
      val openText2: TextView = itemView.findViewById(R.id.open_tv2)

      openText1.text = data.sector
      openText2.text = data.desc
    }
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    (holder as? ViewHolder)?.onBind(mData[position])
  }

  override fun getItemCount(): Int {
    return mData.size
  }

  fun setItem(input: MutableList<OpenItem>) {
    mData = input
  }
}
