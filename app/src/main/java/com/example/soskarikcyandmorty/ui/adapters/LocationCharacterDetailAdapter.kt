package com.example.soskarikcyandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soskarikcyandmorty.bases.BaseDiffUtilItemCallback
import com.example.soskarikcyandmorty.databinding.ItemLocationCharacterDetailBinding
import com.example.soskarikcyandmorty.domain.models.LocationModel

class LocationCharacterDetailAdapter :
    PagingDataAdapter<LocationModel, LocationCharacterDetailAdapter.ViewHolder>(
        BaseDiffUtilItemCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemLocationCharacterDetailBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        getItem(position)?.let { holder.onBind(it) }
    }

    class ViewHolder(private val binding: ItemLocationCharacterDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(it: LocationModel) = with(binding) {
            mainTitleItemCharacterDetail.text = it.name
            descriptionTextItemCharacterDetail.text = it.type
            itemLocationDimensionCharacterDetail.text = it.dimension
        }
    }
}