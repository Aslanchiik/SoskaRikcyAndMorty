package com.example.soskarikcyandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.EpisodeModel
import com.example.soskarikcyandmorty.databinding.ItemEpisodeCharacterDetailBinding
import com.example.soskarikcyandmorty.presentation.base.BaseDiffUtilItemCallback
import com.example.soskarikcyandmorty.presentation.models.EpisodeModelUI

class EpisodeCharacterDetailAdapter :
    PagingDataAdapter<EpisodeModelUI, EpisodeCharacterDetailAdapter.ViewHolder>(
        BaseDiffUtilItemCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemEpisodeCharacterDetailBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    class ViewHolder(private val binding: ItemEpisodeCharacterDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(it: EpisodeModelUI) = with(binding) {
            titleMainItemCharacterDetail.text = it.name
            textDescriptionItemCharacterDetail.text = it.episode
        }
    }
}