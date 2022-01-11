package com.example.soskarikcyandmorty.ui.fragments.episode.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.bases.BaseFragment
import com.example.soskarikcyandmorty.databinding.FragmentEpisodeDetailBinding
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment :
    BaseFragment<FragmentEpisodeDetailBinding>(R.layout.fragment_episode_detail) {

    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
    private val viewModel: EpisodeDetailViewModel by viewModels()
    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun initialize() {
        viewModel.fetchCharacterId(args.id)

    }

    override fun setupObserves() {
        with(binding) {
            viewModel.fetchEpisodeId.observe(viewLifecycleOwner, {
                when (it) {
                    is UIState.Error -> {
                    }
                    is UIState.Loading -> {
                    }
                    is UIState.Success -> {
                        episodeDetailName.text = it.data.name
                        episodeAirDate.text = it.data.air_date
                        episodeCreated.text = it.data.created
                    }
                }
            })
        }
    }
}
