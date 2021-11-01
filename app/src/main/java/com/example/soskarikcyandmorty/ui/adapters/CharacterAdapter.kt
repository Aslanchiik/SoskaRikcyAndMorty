package com.example.soskarikcyandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soskarikcyandmorty.bases.BaseDiffUtilItemCallback
import com.example.soskarikcyandmorty.databinding.ItemCharacterBinding
import com.example.soskarikcyandmorty.domain.models.CharacterModel
import com.example.soskarikcyandmorty.utils.loadImagesWithGlide

class CharacterAdapter :
    ListAdapter<CharacterModel, CharacterAdapter.ViewHolder>(BaseDiffUtilItemCallback<CharacterModel>()) {

    class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(it: CharacterModel) {
            with(binding) {
                loadImagesWithGlide(itemCharacterImage, it.image, loaderItemCharacter)
                itemCharacterText.text = it.name
//                itemCharacterStatus.text = it.status
                itemCharacterSpecies.text = it.species
//                itemCharacterLocation.text = it.location./*/toString()
                 when(it.status){
                     "Alive" -> {

                     }
                 }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }
}