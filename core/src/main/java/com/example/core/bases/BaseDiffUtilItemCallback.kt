package com.example.core.bases

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.common.base.BaseInterfaceCallback

class BaseDiffUtilItemCallback<B : BaseInterfaceCallback> : DiffUtil.ItemCallback<B>() {

    override fun areItemsTheSame(oldItem: B, newItem: B): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: B, newItem: B): Boolean {
        return oldItem == newItem
    }
}