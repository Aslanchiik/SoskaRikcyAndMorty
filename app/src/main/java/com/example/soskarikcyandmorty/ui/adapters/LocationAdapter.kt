package com.example.soskarikcyandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.bases.BaseDiffUtilItemCallback
import com.example.core.exensions.setOnSingleClickListener
import com.example.domain.models.LocationModel
import com.example.soskarikcyandmorty.databinding.ItemLocationBinding

class LocationAdapter(
    val onItemClick: (name: String, id: Int) -> Unit
) : PagingDataAdapter<LocationModel, LocationAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class ViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnSingleClickListener {
                getItem(absoluteAdapterPosition)?.apply {
                    onItemClick(name, id)
                }
            }
        }

        fun onBind(it: LocationModel) = with(binding) {
            mainTitle.text = it.name
            descriptionText.text = it.type
            itemLocationDimension.text = it.dimension
            itemLocationPlanet.isVisible = it.type == "Planet"
        }
    }
}