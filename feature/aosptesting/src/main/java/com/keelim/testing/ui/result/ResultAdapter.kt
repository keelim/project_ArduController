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
package com.keelim.testing.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.keelim.testing.R

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.MyViewHolder>() {
  private var list: List<Long> = listOf()

  class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val textView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_view, parent, false) as TextView
    return MyViewHolder(textView)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.textView.text = position.toString() + ": " + list[position].toString()
  }

  override fun getItemCount() = list.size

  fun setItem(items: List<Long>) {
    list = items
  }
}
