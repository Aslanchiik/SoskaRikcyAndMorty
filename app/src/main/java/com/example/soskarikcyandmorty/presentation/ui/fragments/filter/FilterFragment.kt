package com.example.soskarikcyandmorty.presentation.ui.fragments.filter

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.databinding.FragmentFilterBinding
import com.example.soskarikcyandmorty.presentation.base.BaseFragment
import com.example.soskarikcyandmorty.presentation.extensions.searchItem
import com.example.soskarikcyandmorty.presentation.models.RickyAndMortyModelUI
import com.example.soskarikcyandmorty.presentation.models.toCharacterModelUI
import com.example.soskarikcyandmorty.presentation.models.toEpisodeModelUI
import com.example.soskarikcyandmorty.presentation.models.toLocationModelUI
import com.example.soskarikcyandmorty.presentation.ui.adapters.FilterAdapter
import com.example.soskarikcyandmorty.presentation.ui.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : BaseFragment<FragmentFilterBinding>(R.layout.fragment_filter) {

    override val binding by viewBinding(FragmentFilterBinding::bind)
    private val viewModel: FilterViewModel by viewModels()
    private var list = arrayListOf<RickyAndMortyModelUI>()
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
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.fetchCharacterFilter.collect { it ->
                when (it) {
                    is UIState.Error -> {
                        Log.e("tag", "error")
                    }
                    is UIState.Loading -> {
                        Log.e("tag", "Loading")
                    }
                    is UIState.Success -> it.data.let { data ->
                        val sortedList = data.sortedByDescending { list ->
                            list.created
                        }
                        list.addAll(sortedList.map { it.toCharacterModelUI() })
                    }
                }
                filterAdapter.notifyDataSetChanged()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun locationObserves() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.fetchLocationFilter.collect { it ->
                when (it) {
                    is UIState.Error -> {
                        Log.e("tag", it.error)
                    }
                    is UIState.Loading -> {
                        Log.e("tag", "loading")
                    }
                    is UIState.Success -> it.data
                        .let { data ->
                            val sortedList = data.sortedByDescending { list ->
                                list.created
                            }
                            list.addAll(sortedList.map { it.toLocationModelUI() })
                        }
                }
                filterAdapter.notifyDataSetChanged()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun episodeObserves() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.fetchEpisodeFilter.collect { it ->
                when (it) {
                    is UIState.Error -> {
                        Log.e("tag", it.error)
                    }
                    is UIState.Loading -> {
                        Log.e("tag", "loading")
                    }
                    is UIState.Success -> it.data
                        .let { data ->
                            val sortedList = data.sortedByDescending { list ->
                                list.created
                            }
                            list.addAll(sortedList.map { it.toEpisodeModelUI() })
                        }
                }
                filterAdapter.notifyDataSetChanged()
            }
        }
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