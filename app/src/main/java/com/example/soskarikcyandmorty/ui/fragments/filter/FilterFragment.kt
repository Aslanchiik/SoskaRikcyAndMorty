package com.example.soskarikcyandmorty.ui.fragments.filter

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.bases.BaseFragment
import com.example.soskarikcyandmorty.common.exensions.searchItem
import com.example.soskarikcyandmorty.databinding.FragmentFilterBinding
import com.example.soskarikcyandmorty.domain.models.RickyAndMortyModel
import com.example.soskarikcyandmorty.domain.models.toCharacterModel
import com.example.soskarikcyandmorty.domain.models.toEpisodeModel
import com.example.soskarikcyandmorty.domain.models.toLocationModel
import com.example.soskarikcyandmorty.presentation.state.UIState
import com.example.soskarikcyandmorty.ui.adapters.FilterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : BaseFragment<FragmentFilterBinding>(R.layout.fragment_filter) {

    override val binding by viewBinding(FragmentFilterBinding::bind)
    private val viewModel: FilterViewModel by viewModels()
    private var list = arrayListOf<RickyAndMortyModel>()
    private val filterAdapter: FilterAdapter = FilterAdapter(
        list,
        this::onCharacterItemClick,
        this::onLocationItemClick,
        this::onEpisodeItemClick
    )

    override fun setupViews() {
        binding.filterRecView.adapter = filterAdapter
    }

    override fun setupObserves() {
        charactersObserves()
        locationObserves()
        episodeObserves()
    }

    override fun setupRequest() {
        fetchFilter("")
    }

    private fun fetchFilter(s: String) {
        viewModel.fetchCharacterFilter(s)
        viewModel.fetchLocationFilter(s)
        viewModel.fetchEpisodeFilter(s)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun charactersObserves() {
        viewModel.fetchCharacterFilter.observe(viewLifecycleOwner, { it ->
            when (it) {
                is UIState.Error -> {
                    Log.e("tag", "error")
                }
                is UIState.Loading -> {
                    Log.e("tag", "Loading")
                }
                is UIState.Success -> it.data.map {
                    it.toCharacterModel()
                }.let {
                    val sortedList = it.sortedByDescending { list ->
                        list.charactersModel.created
                    }
                    list.addAll(sortedList)
                }
            }
            filterAdapter.notifyDataSetChanged()
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun locationObserves() {
        viewModel.fetchLocationFilter.observe(viewLifecycleOwner, { it ->
            binding.filterLoader.isVisible = it is UIState.Loading
            when (it) {
                is UIState.Error -> {
                    Log.e("tag", it.error)
                }
                is UIState.Loading -> {
                    Log.e("tag", "loading")
                }
                is UIState.Success -> it.data.map {
                    it.toLocationModel()
                }.let {
                    val sortedList = it.sortedByDescending { list ->
                        list.locationModel.created
                    }
                    list.addAll(sortedList)
                }
            }
            filterAdapter.notifyDataSetChanged()
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun episodeObserves() {
        viewModel.fetchEpisodeFilter.observe(viewLifecycleOwner, { it ->
            when (it) {
                is UIState.Error -> {
                    Log.e("tag", it.error)
                }
                is UIState.Loading -> {
                    Log.e("tag", "loading")
                }
                is UIState.Success -> it.data.map {
                    it.toEpisodeModel()
                }.let {
                    val sortedList = it.sortedByDescending { list ->
                        list.episodeModel.created
                    }
                    list.addAll(sortedList)
                }
            }
            filterAdapter.notifyDataSetChanged()
        })
    }

    override fun setupListener() {
        searchItems()
    }

    private fun searchItems() {
        binding.searchAllParameters.searchItem {
            fetchFilter(it.toString())
            list.clear()
        }
    }

    private fun onCharacterItemClick(name: String, id: Int) {
        findNavController().navigate(
            FilterFragmentDirections.actionFilterFragmentToCharacterDetailFragment(
                label = "${getString(R.string.fragment_detail_character)}$name",
                id = id
            )
        )
    }

    private fun onLocationItemClick(name: String, id: Int) {
        findNavController().navigate(
            FilterFragmentDirections.actionFilterFragmentToLocationDetailFragment(
                label = "${getString(R.string.fragment_detail_location)}$name",
                id = id
            )
        )
    }

    private fun onEpisodeItemClick(name: String, id: Int) {
        findNavController().navigate(
            FilterFragmentDirections.actionFilterFragmentToEpisodeDetailFragment(
                label = "${getString(R.string.fragment_detail_episode)}$name",
                id = id
            )
        )
    }
}