package com.example.soskarikcyandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.bases.BaseDiffUtilItemCallback
import com.example.soskarikcyandmorty.common.exensions.loadImagesWithGlide
import com.example.soskarikcyandmorty.common.exensions.setOnSingleClickListener
import com.example.soskarikcyandmorty.common.exensions.setOnSingleLongClickListener
import com.example.soskarikcyandmorty.databinding.ItemCharacterBinding
import com.example.soskarikcyandmorty.domain.models.CharacterModel

class CharacterAdapter(
    val onItemClick: (name: String, id: Int) -> Unit,
    val onLongClick: (image: String) -> Unit
) : PagingDataAdapter<CharacterModel, CharacterAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.ViewHolder {
        return ViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterAdapter.ViewHolder, position: Int) {
        getItem(position).let { holder.onBind(it) }
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
        }

        fun onBind(it: CharacterModel?) = with(binding) {
            if (it != null) {
                itemCharacterImage.loadImagesWithGlide(it.image, loaderItemCharacter)
            }
            itemCharacterText.text = it?.name
            itemCharacterSpecies.text = it?.species
            itemCharacterStatus.text = it?.status
            when (it?.status) {
                "Alive"
                -> itemSmallRectangle.setBackgroundResource(R.drawable.draw_alive)
                "Dead"
                -> itemSmallRectangle.setBackgroundResource(R.drawable.draw_dead)
                "unknown"
                -> itemSmallRectangle.setBackgroundResource(R.drawable.draw_unknown)
            }
            itemLastLocation.text = it?.location?.name
        }
    }
}