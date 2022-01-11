package com.example.soskarikcyandmorty.ui.fragments.location.detail

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.bases.BaseFragment
import com.example.soskarikcyandmorty.databinding.FragmentLocationDetailBinding
import com.example.soskarikcyandmorty.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment :
    BaseFragment<FragmentLocationDetailBinding>(R.layout.fragment_location_detail) {

    override val binding by viewBinding(FragmentLocationDetailBinding::bind)
    private val viewModel: LocationDetailViewModel by viewModels()
    private val args: LocationDetailFragmentArgs by navArgs()

    override fun initialize() {
        viewModel.fetchCharacterId(args.id)
    }

    override fun setupObserves() {
        with(binding) {
            viewModel.fetchLocationId.observe(viewLifecycleOwner, {
                when (it) {
                    is UIState.Error -> {
                    }
                    is UIState.Loading -> {
                    }
                    is UIState.Success -> {
                        Log.e("tag", it.data.type)
                        locationNameDetail.text = it.data.name
                        locationDetailDimension.text = it.data.dimension
                        locationDetailType.text = it.data.type
                    }
                }
            })
        }
    }
}