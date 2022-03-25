package com.example.soskarikcyandmorty.presentation.ui.adapters.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.databinding.ItemCharacterBinding
import com.example.soskarikcyandmorty.databinding.ItemEpisodeBinding
import com.example.soskarikcyandmorty.databinding.ItemLocationBinding
import com.example.soskarikcyandmorty.presentation.extensions.loadImagesWithGlide
import com.example.soskarikcyandmorty.presentation.extensions.setOnSingleClickListener
import com.example.soskarikcyandmorty.presentation.models.RickyAndMortyModelUI

sealed class FilterViewHolderAdapter<out V : ViewBinding>(
    binding: V
) : RecyclerView.ViewHolder(binding.root) {

    class CharacterViewHolder(
        private val onItemClick: (name: String, id: Int) -> Unit,
        private val binding: ItemCharacterBinding,
    ) : FilterViewHolderAdapter<ItemCharacterBinding>(binding) {
        fun onBind(item: RickyAndMortyModelUI.CharactersModelUI) =
            with(binding) {
                itemCharacterImage.loadImagesWithGlide(
                    item.charactersModel.image,
                    loaderItemCharacter
                )
                itemCharacterText.text = item.charactersModel.name
                itemCharacterSpecies.text = item.charactersModel.species
                itemCharacterStatus.text = item.charactersModel.status
                when (item.charactersModel.status) {
                    "Alive"
                    -> itemSmallRectangle.setBackgroundResource(R.drawable.draw_alive)
                    "Dead"
                    -> itemSmallRectangle.setBackgroundResource(R.drawable.draw_dead)
                    "unknown"
                    -> itemSmallRectangle.setBackgroundResource(R.drawable.draw_unknown)
                }
                itemLastLocation.text = item.charactersModel.location.name
                itemView.setOnSingleClickListener {
                    onItemClick(item.charactersModel.name, item.charactersModel.id)
                }
            }
    }


    class LocationViewHolder(
        private val onItemClick: (name: String, id: Int) -> Unit,
        private val binding: ItemLocationBinding,
    ) : FilterViewHolderAdapter<ItemLocationBinding>(binding) {
        fun onBind(item: RickyAndMortyModelUI.LocationsModelUI) = with(binding) {
            mainTitle.text = item.locationModel.name
            descriptionText.text = item.locationModel.type
            itemLocationDimension.text = item.locationModel.dimension
            itemLocationPlanet.isVisible = item.locationModel.type == "Planet"
            itemView.setOnSingleClickListener {
                onItemClick(item.locationModel.name, item.locationModel.id)
            }
        }
    }

    class EpisodeViewHolder(
        private val onItemClick: (name: String, id: Int) -> Unit,
        private val binding: ItemEpisodeBinding,
    ) : FilterViewHolderAdapter<ItemEpisodeBinding>(binding) {
        fun onBind(item: RickyAndMortyModelUI.EpisodesModelUI) = with(binding) {
            mainTitle.text = item.episodeModel.name
            descriptionText.text = item.episodeModel.episode
            itemView.setOnSingleClickListener {
                onItemClick(item.episodeModel.name, item.episodeModel.id)
            }
        }
    }
}
