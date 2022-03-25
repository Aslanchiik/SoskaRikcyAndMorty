package com.example.soskarikcyandmorty.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.soskarikcyandmorty.presentation.ui.fragments.character.detail.episodecharacterdetail.EpisodeCharacterDetailFragment
import com.example.soskarikcyandmorty.presentation.ui.fragments.character.detail.locationcharacterdetail.LocationCharacterDetailFragment

class CharacterDetailViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> {
                LocationCharacterDetailFragment()
            }
            else -> EpisodeCharacterDetailFragment()
        }
    }
}