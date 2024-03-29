package com.example.soskarikcyandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.constants.Constants.PAYLOAD_TITLE
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.databinding.ItemCharacterBinding
import com.example.soskarikcyandmorty.presentation.base.BaseDiffUtilItemCallback
import com.example.soskarikcyandmorty.presentation.extensions.loadImagesWithGlide
import com.example.soskarikcyandmorty.presentation.extensions.setOnSingleClickListener
import com.example.soskarikcyandmorty.presentation.extensions.setOnSingleLongClickListener
import com.example.soskarikcyandmorty.presentation.models.CharacterModelUI

class CharacterAdapter(
    val onItemClick: (name: String, id: Int) -> Unit,
    val fetchFirstSeenIn: (position: Int, episodeUrl: String) -> Unit,
    val onLongClick: (image: String) -> Unit
) : PagingDataAdapter<CharacterModelUI, CharacterAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.ViewHolder {
        return ViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterAdapter.ViewHolder, position: Int) {
        getItem(position).let { it?.let { it1 -> holder.onBind(it1) } }
    }

    fun setFirstSeenIn(position: Int, firstSeenIn: String) {
        getItem(position)?.firstSeenIn = firstSeenIn
        notifyItemChanged(position, PAYLOAD_TITLE)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            for (payload in payloads) {
                if (payload == PAYLOAD_TITLE) {
                    holder.setupFirstSeenIn(
                        getItem(position)?.firstSeenIn.toString(),
                        getItem(position)?.episode?.first().toString()
                    )
                }
            }
        }
    }

    inner class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnSingleClickListener {
                getItem(absoluteAdapterPosition)?.apply {
                    onItemClick(name, id)
                }
            }
            itemView.setOnSingleLongClickListener {
                getItem(absoluteAdapterPosition)?.apply {
                    onLongClick(image)
                }
                false
            }

            binding.itemLastLocation.setOnClickListener {
                getItem(absoluteAdapterPosition)
            }

            binding.itemFirstSeenIn.setOnClickListener {
                getItem(absoluteAdapterPosition)
            }
        }

        fun onBind(it: CharacterModelUI) = with(binding) {
            itemCharacterImage.loadImagesWithGlide(it.image, loaderItemCharacter)
            itemCharacterText.text = it.name
            itemCharacterSpecies.text = it.species
            itemCharacterStatus.text = it.status
            when (it.status) {
                "Alive"
                -> itemSmallRectangle.setBackgroundResource(R.drawable.draw_alive)
                "Dead"
                -> itemSmallRectangle.setBackgroundResource(R.drawable.draw_dead)
                "unknown"
                -> itemSmallRectangle.setBackgroundResource(R.drawable.draw_unknown)
            }
            itemLastLocation.text = it.location.name
            setupFirstSeenIn(it.firstSeenIn, it.episode.first())
        }

        fun setupFirstSeenIn(firstSeenIn: String, episode: String) = with(binding) {
            if (firstSeenIn.isEmpty()) {
                fetchFirstSeenIn(absoluteAdapterPosition, episode)
            } else {
                itemFirstSeenIn.text = firstSeenIn
            }
        }
    }
}