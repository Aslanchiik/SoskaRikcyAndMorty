package com.example.soskarikcyandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soskarikcyandmorty.bases.BaseDiffUtilItemCallback
import com.example.soskarikcyandmorty.databinding.ItemEpisodeCharacterDetailBinding
import com.example.soskarikcyandmorty.domain.models.EpisodeModel

class EpisodeCharacterDetailAdapter :
    PagingDataAdapter<EpisodeModel, EpisodeCharacterDetailAdapter.ViewHolder>(
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
        fun onBind(it: EpisodeModel) = with(binding) {
            titleMainItemCharacterDetail.text = it.name
            textDescriptionItemCharacterDetail.text = it.episode
        }
    }
}