package com.example.soskarikcyandmorty.presentation.ui.fragments.character.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.databinding.FragmentDetailBinding
import com.example.soskarikcyandmorty.presentation.base.BaseFragment
import com.example.soskarikcyandmorty.presentation.extensions.setImage
import com.example.soskarikcyandmorty.presentation.ui.adapters.CharacterDetailViewPagerAdapter
import com.example.soskarikcyandmorty.presentation.ui.state.UIState
import com.example.soskarikcyandmorty.utils.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    override val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel: CharacterDetailViewModel by viewModels()
    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun initialize() {
        viewModel.fetchCharacterId(args.id)
    }

    override fun setupViews() {
        setupViewPager()
        setupTabLayout()
    }

    override fun setupListener() {
        binding.mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupViewPager() {
        binding.viewPager.adapter = CharacterDetailViewPagerAdapter(this)
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer())
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
            viewModel.fetchCharacterDetailId.collectUIState {
                when (it) {
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {
                        mainToolbar.title = it.data.name
                        logoCharacter.setImage(it.data.image)
                        nameTxt.text = it.data.name
                        genderTxt.text = it.data.gender
                    }
                    is UIState.Idle -> {}
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