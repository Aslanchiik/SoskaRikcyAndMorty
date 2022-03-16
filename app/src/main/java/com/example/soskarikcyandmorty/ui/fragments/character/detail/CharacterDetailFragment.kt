package com.example.soskarikcyandmorty.ui.fragments.character.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.bases.BaseFragment
import com.example.soskarikcyandmorty.common.exensions.setImage
import com.example.soskarikcyandmorty.databinding.FragmentDetailBinding
import com.example.soskarikcyandmorty.presentation.state.UIState
import com.example.soskarikcyandmorty.ui.adapters.CharacterDetailViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    override val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel: CharacterDetailViewModel by viewModels()
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun initialize() {
        viewModel.fetchCharacterId(args.id)
    }

    override fun setupViews() {
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        binding.viewPager.adapter = CharacterDetailViewPagerAdapter(this)
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayoutDetail, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Episodes"
                1 -> "Locations"
                else -> "Nothing"
            }
        }.attach()
    }

    override fun setupObserves() {
        with(binding) {
            viewModel.fetchCharacterDetailId.subscribe {
                when (it) {
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {
                        mainToolbar.title = it.data.name
                        logoCharacter.setImage(it.data.image)
                        nameTxt.text = it.data.name
                        genderTxt.text = it.data.gender
                    }
                }
            }
        }
//        with(binding) {
//            viewModel.fetchCharacterDetailId.subscribe {
//                when (it) {
//                    is UIState.Error -> {
//                        Log.e("tag", "error")
//
//                    }
//                    is UIState.Loading -> {
//                        Log.e("tag", "loading")
//                    }
//                    is UIState.Success -> {
//                        detailImage.loadImagesWithGlide(it.data.image, loaderImage)
//                        detailName.text = it.data.name
//                        detailGender.text = it.data.gender
//                        detailSpecies.text = it.data.species
//                        detailStatus.text = it.data.status
//                    }
//                }
//            }
//        }
    }
}