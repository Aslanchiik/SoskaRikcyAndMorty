package com.example.soskarikcyandmorty.presentation.ui.fragments.character.detail.locationcharacterdetail

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.databinding.FragmentLocationCharacterDetailBinding
import com.example.soskarikcyandmorty.presentation.base.BaseFragment
import com.example.soskarikcyandmorty.presentation.ui.adapters.LocationCharacterDetailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationCharacterDetailFragment :
    BaseFragment<FragmentLocationCharacterDetailBinding>(R.layout.fragment_location_character_detail) {

    override val binding by viewBinding(FragmentLocationCharacterDetailBinding::bind)
    private val viewModel: LocationCharacterDetailViewModel by viewModels()
    private val locationCharacterAdapter = LocationCharacterDetailAdapter()

    override fun initialize() {
        binding.locationCharacterDetailRecView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = locationCharacterAdapter
        }
    }

    override fun setupRequest() {
        viewModel.fetchLocations("", "", "")
    }

    override fun setupObserves() {
        viewModel.locationCharacterDetailState.collectPaging {
            locationCharacterAdapter.submitData(it)
        }
    }
}