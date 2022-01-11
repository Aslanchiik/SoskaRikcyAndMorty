package com.example.soskarikcyandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.databinding.ItemCharacterBinding
import com.example.soskarikcyandmorty.databinding.ItemEpisodeBinding
import com.example.soskarikcyandmorty.databinding.ItemLocationBinding
import com.example.soskarikcyandmorty.domain.models.RickyAndMortyModel
import com.example.soskarikcyandmorty.ui.adapters.viewholder.FilterViewHolderAdapter

class FilterAdapter(
    private var list: ArrayList<RickyAndMortyModel>,
    private var characterItemClick: (name: String, id: Int) -> Unit,
    private var locationItemClick: (name: String, id: Int) -> Unit,
    private var episodeItemClick: (name: String, id: Int) -> Unit
) : RecyclerView.Adapter<FilterViewHolderAdapter<ViewBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterViewHolderAdapter<ViewBinding> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_character -> FilterViewHolderAdapter.CharacterViewHolder(
                characterItemClick,
                ItemCharacterBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            R.layout.item_location -> FilterViewHolderAdapter.LocationViewHolder(
                locationItemClick,
                ItemLocationBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            R.layout.item_episode -> FilterViewHolderAdapter.EpisodeViewHolder(
                episodeItemClick,
                ItemEpisodeBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> throw IllegalAccessException("Wrong view type provide")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is RickyAndMortyModel.CharactersModel -> R.layout.item_character
            is RickyAndMortyModel.EpisodesModel -> R.layout.item_episode
            is RickyAndMortyModel.LocationsModel -> R.layout.item_location
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(
        holder: FilterViewHolderAdapter<ViewBinding>,
        position: Int
    ) {
        when (holder) {
            is FilterViewHolderAdapter.CharacterViewHolder -> {
                holder.onBind(list[position] as RickyAndMortyModel.CharactersModel)
            }

            is FilterViewHolderAdapter.EpisodeViewHolder -> {
                holder.onBind(list[position] as RickyAndMortyModel.EpisodesModel)
            }

            is FilterViewHolderAdapter.LocationViewHolder -> {
                holder.onBind(list[position] as RickyAndMortyModel.LocationsModel)
            }
        }
    }
}