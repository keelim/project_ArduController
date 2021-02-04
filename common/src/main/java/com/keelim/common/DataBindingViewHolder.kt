package com.keelim.common

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import androidx.databinding.library.baseAdapters.BR

class DataBindingViewHolder<T>(val binding: ViewBinding, private val viewModel : ViewModel): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T){
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }
}