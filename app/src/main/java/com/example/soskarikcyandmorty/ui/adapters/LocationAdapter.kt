package com.example.soskarikcyandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soskarikcyandmorty.bases.BaseDiffUtilItemCallback
import com.example.soskarikcyandmorty.databinding.ItemLocationBinding
import com.example.soskarikcyandmorty.domain.models.LocationModel

class LocationAdapter :
    ListAdapter<LocationModel, LocationAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    class ViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(it: LocationModel) {
            binding.mainTitle.text = it.name
            binding.descriptionText.text = it.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(
                    parent.context,
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }

    }
}