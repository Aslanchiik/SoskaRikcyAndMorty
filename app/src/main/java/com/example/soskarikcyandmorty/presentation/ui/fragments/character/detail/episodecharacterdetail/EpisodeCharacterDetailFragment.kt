package com.example.soskarikcyandmorty.presentation.ui.fragments.character.detail.episodecharacterdetail

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.databinding.FragmentEpisodeCharacterDetailBinding
import com.example.soskarikcyandmorty.presentation.base.BaseFragment
import com.example.soskarikcyandmorty.presentation.ui.adapters.EpisodeCharacterDetailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeCharacterDetailFragment :
    BaseFragment<FragmentEpisodeCharacterDetailBinding>(R.layout.fragment_episode_character_detail) {

    override val binding by viewBinding(FragmentEpisodeCharacterDetailBinding::bind)
    private val viewModel: EpisodeCharacterDetailViewModel by viewModels()
    private val episodeCharacterDetailAdapter = EpisodeCharacterDetailAdapter()

    override fun initialize() {
        binding.episodeCharacterDetailRecView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodeCharacterDetailAdapter
        }
    }

    override fun setupRequest() {
        viewModel.fetchEpisodes("", "")
    }

    override fun setupObserves() {
        viewModel.episodeCharacterDetailState.subscribePaging {
            episodeCharacterDetailAdapter.submitData(it)
        }
    }
}