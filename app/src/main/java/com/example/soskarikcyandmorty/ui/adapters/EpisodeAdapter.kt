package com.example.soskarikcyandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soskarikcyandmorty.bases.BaseDiffUtilItemCallback
import com.example.soskarikcyandmorty.databinding.ItemEpisodeBinding
import com.example.soskarikcyandmorty.domain.models.EpisodeModel

class EpisodeAdapter :
    ListAdapter<EpisodeModel, EpisodeAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    class ViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(it: EpisodeModel) {
            binding.mainTitle.text = it.name
            binding.descriptionText.text = it.episode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEpisodeBinding.inflate(
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
}