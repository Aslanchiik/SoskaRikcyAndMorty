package com.example.soskarikcyandmorty.ui.fragments.character.deatil

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.bases.BaseFragment
import com.example.soskarikcyandmorty.common.exensions.loadImagesWithGlide
import com.example.soskarikcyandmorty.databinding.FragmentDetailBinding
import com.example.soskarikcyandmorty.presentation.state.UIState
import com.example.soskarikcyandmorty.ui.fragments.character.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    override val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel: CharacterViewModel by activityViewModels()
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun initialize() {
        viewModel.fetchCharacterId(args.id)
    }

    override fun setupObserves() {
        with(binding) {
            viewModel.fetchCharacterId.observe(viewLifecycleOwner, {
                when (it) {
                    is UIState.Error -> {
                        Log.e("tag", "error")
                    }
                    is UIState.Loading -> {
                        Log.e("tag", "error")
                    }
                    is UIState.Success -> {
                        detailImage.loadImagesWithGlide(it.data.image, loaderImage)
                        detailName.text = it.data.name
                        detailGender.text = it.data.gender
                        detailSpecies.text = it.data.species
                        detailStatus.text = it.data.status
                    }
                }
            })
        }
    }
}