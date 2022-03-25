package com.example.soskarikcyandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.bases.BaseDiffUtilItemCallback
import com.example.core.exensions.setOnSingleClickListener
import com.example.domain.models.EpisodeModel
import com.example.soskarikcyandmorty.databinding.ItemEpisodeBinding

class EpisodeAdapter(
    val onItemClick: (name: String, id: Int) -> Unit
) : PagingDataAdapter<EpisodeModel, EpisodeAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

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

        fun onBind(it: EpisodeModel) = with(binding) {
            mainTitle.text = it.name
            descriptionText.text = it.episode
        }
    }
}