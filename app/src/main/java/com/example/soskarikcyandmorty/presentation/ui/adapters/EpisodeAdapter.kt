package com.example.soskarikcyandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soskarikcyandmorty.databinding.ItemEpisodeBinding
import com.example.soskarikcyandmorty.presentation.base.BaseDiffUtilItemCallback
import com.example.soskarikcyandmorty.presentation.extensions.setOnSingleClickListener
import com.example.soskarikcyandmorty.presentation.models.EpisodeModelUI

class EpisodeAdapter(
    val onItemClick: (name: String, id: Int) -> Unit
) : PagingDataAdapter<EpisodeModelUI, EpisodeAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class ViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnSingleClickListener {
                getItem(absoluteAdapterPosition)?.apply {
                    onItemClick(name, id)
                }
            }
        }

        fun onBind(it: EpisodeModelUI) = with(binding) {
            mainTitle.text = it.name
            descriptionText.text = it.episode
        }
    }
}